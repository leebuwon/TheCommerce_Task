package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

public class NotFoundUsernameException extends GlobalException {
    public NotFoundUsernameException(GlobalErrorCode code) {
        super(code);
    }
}
