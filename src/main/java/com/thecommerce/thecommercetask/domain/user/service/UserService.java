package com.thecommerce.thecommercetask.domain.user.service;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private void checkDuplicate(JoinUserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())){
            throw new IllegalArgumentException("현재 존재하는 회원아이디 입니다.");
        }

        if (userRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("현재 존재하는 이메일입니다.");
        }

        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())){
            throw new IllegalArgumentException("현재 존재하는 핸드폰 번호입니다.");
        }
    }
}