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
public class JoinUserDto {

    private String username;
    private String password;
    private String nickname;
    private String fullName;
    private String phoneNumber;
    private String email;

    // dto -> entity
    public User toEntity(JoinUserDto dto){
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
