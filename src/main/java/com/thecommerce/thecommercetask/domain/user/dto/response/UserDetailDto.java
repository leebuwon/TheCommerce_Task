package com.thecommerce.thecommercetask.domain.user.dto.response;

import com.thecommerce.thecommercetask.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {

    private String username;
    private String nickname;
    private String fullName;
    private String phoneNumber;
    private String email;
    private LocalDateTime createAt;

    public static UserDetailDto of(User user){
        return UserDetailDto.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .createAt(user.getCreatedAt())
                .build();
    }
}
