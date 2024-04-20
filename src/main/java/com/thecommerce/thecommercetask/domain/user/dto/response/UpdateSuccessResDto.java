package com.thecommerce.thecommercetask.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSuccessResDto {

    private String message;
    private UpdateUserResDto data;

    public static UpdateSuccessResDto of(UpdateUserResDto dto){
        return UpdateSuccessResDto.builder()
                .message("회원 정보 수정에 성공하셨습니다.")
                .data(dto)
                .build();
    }
}
