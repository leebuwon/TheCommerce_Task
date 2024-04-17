package com.thecommerce.thecommercetask.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 30)
    private String password;

    @Size(min = 2, max = 30)
    private String nickname;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;

    @Email
    private String email;
}
