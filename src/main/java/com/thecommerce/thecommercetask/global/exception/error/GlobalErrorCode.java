package com.thecommerce.thecommercetask.global.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCodeType.*;
import static org.springframework.http.HttpStatus.*;

@Getter
public enum GlobalErrorCode {
    INTERNAL_ERROR(INTERNAL_SERVER_ERROR, 500, "서버 에러, 관리자에게 문의하세요", WARNING),
    // 회원 가입 중복 체크 로직
    DUPLICATE_USERNAME_ERROR(CONFLICT,409, "현재 존재하는 회원이름 입니다.", INFO),
    DUPLICATE_EMAIL_ERROR(CONFLICT,409, "현재 존재하는 이메일 입니다.", INFO),
    DUPLICATE_PHONE_NUMBER_ERROR(CONFLICT,409, "현재 존재하는 핸드폰 번호 입니다.", INFO);

    private final HttpStatus status;
    private final int code;
    private final String message;
    private final GlobalErrorCodeType type;

    GlobalErrorCode(HttpStatus status, int code, String message, GlobalErrorCodeType type) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.type = type;
    }
}
