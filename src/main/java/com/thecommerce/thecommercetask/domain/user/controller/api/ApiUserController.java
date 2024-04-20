package com.thecommerce.thecommercetask.domain.user.controller.api;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserReqDto;
import com.thecommerce.thecommercetask.domain.user.dto.request.UpdateUserReqDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UpdateUserResDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UsersResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "users", description = "회원 API")
public interface ApiUserController {

    @Operation(summary = "회원가입")
    ResponseEntity<Void> joinUser(JoinUserReqDto dto);

    @Operation(summary = "회원 전체 조회")
    ResponseEntity<UsersResDto> findAllUser(int page, int size);

    @Operation(summary = "회원 정보 수정")
    ResponseEntity<UpdateUserResDto> updateUser(String username, UpdateUserReqDto dto);
}
