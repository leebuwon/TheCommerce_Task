package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

public class DuplicateEmailException extends GlobalException {
    public DuplicateEmailException(GlobalErrorCode code) {
        super(code);
    }
}
