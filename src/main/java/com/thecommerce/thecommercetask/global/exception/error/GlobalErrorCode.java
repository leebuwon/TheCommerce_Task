package com.thecommerce.thecommercetask.global.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCodeType.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public enum GlobalErrorCode {
    INTERNAL_ERROR(INTERNAL_SERVER_ERROR, 500, "서버 에러, 관리자에게 문의하세요", INFO);

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
