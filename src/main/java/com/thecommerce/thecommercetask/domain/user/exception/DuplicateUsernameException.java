package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

public class DuplicateUsernameException extends GlobalException {
    public DuplicateUsernameException(GlobalErrorCode code) {
        super(code);
    }
}
