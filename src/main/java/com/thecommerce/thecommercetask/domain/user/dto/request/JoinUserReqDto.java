package com.thecommerce.thecommercetask.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thecommerce.thecommercetask.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinUserReqDto {

    @NotNull(message = "회원Id는 필수 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자 조합으로만 가능합니다.")
    @Size(min = 3, max = 15, message = "회원Id는 최소 3자부터 15자까지 가능합니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 값입니다.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 30, message = "비밀번호는 최소 8자부터 30자까지 가능합니다.")
    private String password;

    @NotNull(message = "닉네임은 필수 값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "닉네임은 한글, 영문, 숫자 조합으로만 가능합니다.")
    @Size(min = 2, max = 20, message = "닉네임은 최소 2글자부터 20자까지 가능합니다.")
    private String nickname;

    @NotNull(message = "이름은 필수 값입니다.")
    @Size(min = 2, max = 10, message = "이름은 최소 2자부터 10자까지 가능합니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 가능합니다.")
    private String fullName;

    @NotNull(message = "핸드폰 번호는 필수 값입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호 패턴은 000-0000-0000 입니다.")
    private String phoneNumber;

    @NotNull(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식을 지켜주시기 바랍니다.")
    private String email;

    // dto -> entity
    public User toEntity(JoinUserReqDto dto){
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .build();
    }
}
