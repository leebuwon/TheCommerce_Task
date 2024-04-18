package com.thecommerce.thecommercetask.domain.user.service;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.request.UpdateUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UsersDto;
import com.thecommerce.thecommercetask.domain.user.entity.User;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicateEmailException;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicatePhoneNumberException;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicateUsernameException;
import com.thecommerce.thecommercetask.domain.user.exception.NotFoundUsernameException;
import com.thecommerce.thecommercetask.domain.user.repository.UserRepository;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;
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
    public void join(JoinUserDto dto) {
        checkDuplicate(dto);
        userRepository.save(dto.toEntity(dto));
    }

    public UsersDto list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("createdAt"),
                        Sort.Order.desc("fullName")));
        Page<User> users = userRepository.findAll(pageable);

        return UsersDto.of(users);
    }

    @Transactional
    public void update(String username, UpdateUserDto dto) {
        User user = findByUsername(username);
        user.updateUser(dto.getPassword(), dto.getNickname(), dto.getPhoneNumber(), dto.getEmail());
    }

    private User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundUsernameException(NOT_FOUND_USERNAME_ERROR));
    }

    private void checkDuplicate(JoinUserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())){
            throw new DuplicateUsernameException(DUPLICATE_USERNAME_ERROR);
        }

        if (userRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateEmailException(DUPLICATE_EMAIL_ERROR);
        }

        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())){
            throw new DuplicatePhoneNumberException(DUPLICATE_PHONE_NUMBER_ERROR);
        }
    }
}