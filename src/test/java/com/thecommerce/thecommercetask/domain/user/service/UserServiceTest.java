package com.thecommerce.thecommercetask.domain.user.service;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.entity.User;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicateUsernameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    @DisplayName("회원 가입 실패 - 유효하지 않은 데이터")
    void joinUser_fail() {
        JoinUserDto dto = JoinUserDto.builder()
                .username("te")
                .password("1234")
                .nickname("n")
                .fullName("123")
                .phoneNumber("123-456-7890")
                .email("test@@@naver.com")
                .build();

        assertThatThrownBy(() -> userService.join(dto))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("회원Id 중복으로 인한 테스트 DuplicateUsernameException 발생")
    void throwDuplicateUsernameException_success() {
        JoinUserDto user1 = JoinUserDto.builder()
                .username("testAccount")
                .password("test1234")
                .nickname("testNickname")
                .fullName("이부원")
                .phoneNumber("010-1234-1234")
                .email("test@naver.com")
                .build();

        userService.join(user1);

        JoinUserDto user2 = JoinUserDto.builder()
                .username("testAccount")
                .password("test1234")
                .nickname("testNickname1234")
                .fullName("이부원")
                .phoneNumber("010-1234-7890")
                .email("test1122@naver.com")
                .build();

        assertThatThrownBy(() -> userService.join(user2))
                .isInstanceOf(DuplicateUsernameException.class)
                .hasMessageContaining("현재 존재하는 회원이름 입니다.");
    }
}