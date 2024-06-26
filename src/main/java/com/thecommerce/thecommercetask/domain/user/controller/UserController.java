package com.thecommerce.thecommercetask.domain.user.controller;

import com.thecommerce.thecommercetask.domain.user.controller.api.ApiUserController;
import com.thecommerce.thecommercetask.domain.user.dto.request.JoinUserReqDto;
import com.thecommerce.thecommercetask.domain.user.dto.request.UpdateUserReqDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UpdateSuccessResDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UpdateUserResDto;
import com.thecommerce.thecommercetask.domain.user.dto.response.UsersResDto;
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
    public ResponseEntity<Void> joinUser(@Valid @RequestBody JoinUserReqDto dto) {
        userService.joinUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public ResponseEntity<UsersResDto> findAllUser(@RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "10") int size){

        UsersResDto dto = userService.findAllUser(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UpdateSuccessResDto> updateUser(@PathVariable("username") String username,
                                                          @Valid @RequestBody UpdateUserReqDto dto){
        UpdateSuccessResDto resDto = userService.updateUser(username, dto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }
}