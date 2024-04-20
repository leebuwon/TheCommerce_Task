package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

/**
 * NotFoundUsernameException : 존재하지 않는 회원Id일 경우 exception 발생
 */
public class NotFoundUsernameException extends GlobalException {
    public NotFoundUsernameException(GlobalErrorCode code) {
        super(code);
    }
}
