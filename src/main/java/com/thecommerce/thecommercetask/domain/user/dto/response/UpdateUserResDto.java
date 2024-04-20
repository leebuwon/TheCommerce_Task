package com.thecommerce.thecommercetask.domain.user.dto.response;

import com.thecommerce.thecommercetask.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResDto {

    private String password;
    private String nickname;
    private String phoneNumber;
    private String email;

    public static UpdateUserResDto of(User user){
        return UpdateUserResDto.builder()
                .password(user.getPassword())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }
}
