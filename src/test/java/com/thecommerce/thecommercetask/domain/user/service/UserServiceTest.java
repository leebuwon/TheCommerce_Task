package com.thecommerce.thecommercetask.domain.user.service;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.request.UpdateUserReqDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UsersDto;
import com.thecommerce.thecommercetask.domain.user.entity.User;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicateEmailException;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicatePhoneNumberException;
import com.thecommerce.thecommercetask.domain.user.exception.DuplicateUsernameException;
import com.thecommerce.thecommercetask.domain.user.exception.NotFoundUsernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    JoinUserDto user1;

    @BeforeEach
    void setUp() {
        user1 = JoinUserDto.builder()
                .username("mockUser")
                .password("test1234")
                .nickname("mockNickname")
                .fullName("목유저")
                .phoneNumber("010-1234-1234")
                .email("mock@naver.com")
                .build();
        userService.joinUser(user1);
    }

    @Test
    @DisplayName("회원 가입 성공")
    void joinUser_success() {
        JoinUserDto dto = JoinUserDto.builder()
                .username("testAccount")
                .password("test1234")
                .nickname("testNickname")
                .fullName("테스터")
                .phoneNumber("010-9999-9999")
                .email("test@naver.com")
                .build();

        assertDoesNotThrow(() -> userService.joinUser(dto));

        User user = userService.findByUsername(dto.getUsername());
        assertEquals("testAccount", user.getUsername());
        assertEquals("testNickname", user.getNickname());
        assertEquals("테스터", user.getFullName());
        assertEquals("010-9999-9999", user.getPhoneNumber());
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

        assertThatThrownBy(() -> userService.joinUser(dto))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("회원 목록 조회 성공")
    void listUser_success() {
        JoinUserDto user1 = JoinUserDto.builder()
                .username("testAccount")
                .password("test1234")
                .nickname("testNickname")
                .fullName("테스터")
                .phoneNumber("010-1111-1111")
                .email("test@naver.com")
                .build();

        userService.joinUser(user1);

        UsersDto dto = userService.findAllUser(0, 10);
        assertNotNull(dto);
        assertEquals(10, dto.getPageSize());
        assertEquals(2, dto.getTotalElements());

        assertThat(dto.getData().get(0).getUsername()).isEqualTo("mockUser");
        assertThat(dto.getData().get(1).getUsername()).isEqualTo("testAccount");
        assertThat(dto.getData().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void updateUser_success() {
        User user = userService.findByUsername(user1.getUsername());

        UpdateUserReqDto dto = UpdateUserReqDto.builder()
                .nickname("changeNickname")
                .password("changePassword")
                .phoneNumber("010-1212-1212")
                .email("bw1212@naver.com")
                .build();

        userService.updateUser(user.getUsername(), dto);

        assertThat(user.getNickname()).isEqualTo("changeNickname");
        assertThat(user.getPassword()).isEqualTo("changePassword");
        assertThat(user.getPhoneNumber()).isEqualTo("010-1212-1212");
        assertThat(user.getEmail()).isEqualTo("bw1212@naver.com");
    }

    @Test
    @DisplayName("회원 정보가 존재하지 않을 경우 NotFoundUsernameException 발생")
    void noUsernameThrowsNotFoundUsernameException_success() {
        assertThatThrownBy(() -> userService.findByUsername("testTest"))
                .isInstanceOf(NotFoundUsernameException.class)
                .hasMessageContaining("존재하지 않는 회원Id 입니다.");
    }

    @Test
    @DisplayName("회원Id 중복으로 인한 테스트 DuplicateUsernameException 발생")
    void throwDuplicateUsernameException_success() {
        JoinUserDto dto = JoinUserDto.builder()
                .username("mockUser")
                .password("test1234")
                .nickname("testNickname1234")
                .fullName("이부원")
                .phoneNumber("010-1234-7890")
                .email("test1122@naver.com")
                .build();

        assertThatThrownBy(() -> userService.joinUser(dto))
                .isInstanceOf(DuplicateUsernameException.class)
                .hasMessageContaining("현재 존재하는 회원이름 입니다.");
    }

    @Test
    @DisplayName("이메일 중복으로 인한 테스트 DuplicateEmailException 발생")
    void throwDuplicateEmailException_success() {
        JoinUserDto dto = JoinUserDto.builder()
                .username("testAccount1234")
                .password("test1234")
                .nickname("testNickname1234")
                .fullName("이부원")
                .phoneNumber("010-1234-7890")
                .email("mock@naver.com")
                .build();

        assertThatThrownBy(() -> userService.joinUser(dto))
                .isInstanceOf(DuplicateEmailException.class)
                .hasMessageContaining("현재 존재하는 이메일 입니다.");
    }

    @Test
    @DisplayName("이메일 중복으로 인한 테스트 DuplicatePhoneNumberException 발생")
    void throwDuplicatePhoneNumberException_success() {
        JoinUserDto dto = JoinUserDto.builder()
                .username("testAccount1234")
                .password("test1234")
                .nickname("testNickname1234")
                .fullName("이부원")
                .phoneNumber("010-1234-1234")
                .email("test@naver.com")
                .build();

        assertThatThrownBy(() -> userService.joinUser(dto))
                .isInstanceOf(DuplicatePhoneNumberException.class)
                .hasMessageContaining("현재 존재하는 핸드폰 번호 입니다.");
    }
}