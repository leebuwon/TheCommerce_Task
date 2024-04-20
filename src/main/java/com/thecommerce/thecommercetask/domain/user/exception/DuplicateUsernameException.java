package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

/**
 * DuplicateUsernameException : 회원Id가 중복일 경우 exception 발생
 */
public class DuplicateUsernameException extends GlobalException {
    public DuplicateUsernameException(GlobalErrorCode code) {
        super(code);
    }
}
