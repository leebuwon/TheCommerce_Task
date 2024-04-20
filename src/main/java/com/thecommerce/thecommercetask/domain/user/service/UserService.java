package com.thecommerce.thecommercetask.domain.user.service;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.request.UpdateUserReqDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UpdateUserResDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UsersDto;
import com.thecommerce.thecommercetask.domain.user.entity.User;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicateEmailException;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicatePhoneNumberException;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicateUsernameException;
import com.thecommerce.thecommercetask.domain.user.exception.NotFoundUsernameException;
import com.thecommerce.thecommercetask.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void joinUser(JoinUserDto dto) {
        checkDuplicateUsername(dto.getUsername());
        checkDuplicateEmailAndPhoneNumber(dto.getEmail(), dto.getPhoneNumber());
        userRepository.save(dto.toEntity(dto));
    }

    public UsersDto findAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.asc("createdAt"),
                        Sort.Order.asc("fullName")));
        Page<User> users = userRepository.findAll(pageable);

        return UsersDto.of(users);
    }

    @Transactional
    public UpdateUserResDto updateUser(String username, UpdateUserReqDto dto) {
        User user = findByUsername(username);
        checkDuplicateEmailAndPhoneNumber(dto.getEmail(), dto.getPhoneNumber());
        user.updateUser(dto.getPassword(), dto.getNickname(), dto.getPhoneNumber(), dto.getEmail());
        return UpdateUserResDto.of(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundUsernameException(NOT_FOUND_USERNAME_ERROR));
    }

    private void checkDuplicateUsername(String username) {
        if (userRepository.existsByUsername(username)){
            throw new DuplicateUsernameException(DUPLICATE_USERNAME_ERROR);
        }
    }

    private void checkDuplicateEmailAndPhoneNumber(String email, String phoneNumber) {
        if (userRepository.existsByEmail(email)){
            throw new DuplicateEmailException(DUPLICATE_EMAIL_ERROR);
        }

        if (userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DuplicatePhoneNumberException(DUPLICATE_PHONE_NUMBER_ERROR);
        }
    }
}