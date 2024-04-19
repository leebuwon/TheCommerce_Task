package com.thecommerce.thecommercetask.domain.user.service;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("회원 가입 성공")
    void joinUser_success() {
        JoinUserDto dto = JoinUserDto.builder()
                .username("testAccount")
                .password("test1234")
                .nickname("testNickname")
                .fullName("이부원")
                .phoneNumber("010-1234-1234")
                .email("test@naver.com")
                .build();

        assertDoesNotThrow(() -> userService.join(dto));

        User user = userService.findByUsername(dto.getUsername());
        assertEquals("testAccount", user.getUsername());
        assertEquals("testNickname", user.getNickname());
        assertEquals("이부원", user.getFullName());
        assertEquals("010-1234-1234", user.getPhoneNumber());
        assertEquals("test@naver.com", user.getEmail());
    }
}