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
public class UsersDto {

    private int totalPage;
    private int page;
    private int pageSize;
    private long totalElements;
    private boolean isLast;
    private List<UserDetailDto> data;

    public static UsersDto of(Page<User> users) {
        List<UserDetailDto> userDetailDto = users.stream()
                .map(UserDetailDto::of)
                .collect(Collectors.toList());

        return UsersDto.builder()
                .totalPage(users.getTotalPages())
                .page(users.getNumber())
                .pageSize(users.getSize())
                .totalElements(users.getTotalElements())
                .isLast(users.isLast())
                .data(userDetailDto)
                .build();
    }
}
