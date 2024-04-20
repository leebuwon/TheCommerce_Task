package com.thecommerce.thecommercetask.domain.user.dto.response;

import com.thecommerce.thecommercetask.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResDto {

    private int totalPage;
    private int currentPage;
    private int pageSize;
    private long totalElements;
    private boolean isLast;
    private List<UserDetailResDto> data;

    public static UsersResDto of(Page<User> users) {
        List<UserDetailResDto> userDetailResDto = users.stream()
                .map(UserDetailResDto::of)
                .collect(Collectors.toList());

        return UsersResDto.builder()
                .totalPage(users.getTotalPages())
                .currentPage(users.getNumber())
                .pageSize(users.getSize())
                .totalElements(users.getTotalElements())
                .isLast(users.isLast())
                .data(userDetailResDto)
                .build();
    }
}
