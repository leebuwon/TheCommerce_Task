package com.thecommerce.thecommercetask.domain.user.controller;

import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinUserDto dto) {
        userService.join(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}