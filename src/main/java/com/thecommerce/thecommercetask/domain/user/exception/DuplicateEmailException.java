package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

/**
 * DuplicateEmailException : 이메일이 중복되었을 경우 exception 발생
 */
public class DuplicateEmailException extends GlobalException {
    public DuplicateEmailException(GlobalErrorCode code) {
        super(code);
    }
}
