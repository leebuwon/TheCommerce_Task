package com.thecommerce.thecommercetask.domain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@Table(name = "users")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "회원Id는 필수 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자 조합으로만 가능합니다.")
    @Size(min = 3, max = 15, message = "회원Id는 최소 2자부터 15자까지 가능합니다.")
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull(message = "비밀번호는 필수 값입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 최소 8자부터 30자까지 가능합니다.")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "닉네임은 필수 값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "닉네임은 한글, 영문, 숫자 조합으로만 가능합니다.")
    @Size(min = 2, max = 20, message = "닉네임은 최소 2글자부터 20자까지 가능합니다.")
    @Column(nullable = false)
    private String nickname;

    @NotNull(message = "이름은 필수 값입니다.")
    @Size(min = 2, max = 10, message = "이름은 최소 2자부터 10자까지 가능합니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 가능합니다.")
    @Column(nullable = false)
    private String fullName;

    @NotNull(message = "핸드폰 번호는 필수 값입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 패턴은 000-0000-0000 입니다.")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @NotNull(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식을 지켜주시기 바랍니다.")
    @Column(nullable = false, unique = true)
    private String email;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void updateUser(String password, String nickname, String phoneNumber, String email) {
        this.password = password == null ? this.password : password;
        this.nickname = nickname == null ? this.nickname : nickname;
        this.phoneNumber = phoneNumber == null ? this.phoneNumber : phoneNumber;
        this.email = email == null ? this.email : email;
    }
}
