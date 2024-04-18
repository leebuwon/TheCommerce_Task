package com.thecommerce.thecommercetask.domain.user.controller.api;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.request.UpdateUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UsersDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Tag(name = "users", description = "회원 API")
public interface ApiUserController {

    @Operation(summary = "회원가입")
    ResponseEntity<Void> join(JoinUserDto dto);

    @Operation(summary = "회원 전체 조회")
    ResponseEntity<UsersDto> list(int page, int size);

    @Operation(summary = "회원 정보 수정")
    ResponseEntity<Void> update(String username, UpdateUserDto dto);
}
