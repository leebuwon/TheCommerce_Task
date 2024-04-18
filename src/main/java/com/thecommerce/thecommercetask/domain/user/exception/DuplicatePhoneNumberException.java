package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

public class DuplicatePhoneNumberException extends GlobalException {
    public DuplicatePhoneNumberException(GlobalErrorCode code) {
        super(code);
    }
}
