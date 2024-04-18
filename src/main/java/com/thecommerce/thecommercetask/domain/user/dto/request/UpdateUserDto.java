package com.thecommerce.thecommercetask.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 30, message = "비밀번호는 최소 8자부터 30자까지 가능합니다.")
    private String password;

    @Size(min = 2, max = 30, message = "닉네임은 최소 2글자부터 30자까지 가능합니다.")
    private String nickname;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 패턴은 000-0000-0000 입니다.")
    private String phoneNumber;

    @Email(message = "이메일 형식을 지켜주시기 바랍니다.")
    private String email;
}
