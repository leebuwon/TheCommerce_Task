package com.thecommerce.thecommercetask.domain.user.controller;

import com.thecommerce.thecommercetask.domain.user.controller.api.ApiUserController;
import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.request.UpdateUserDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UsersDto;
import com.thecommerce.thecommercetask.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements ApiUserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@Valid @RequestBody JoinUserDto dto) {
        userService.join(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public ResponseEntity<UsersDto> list(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size){

        UsersDto dto = userService.list(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<Void> update(@PathVariable("username") String username,
                                       @Valid @RequestBody UpdateUserDto dto){
        userService.update(username, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}