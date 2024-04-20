package com.thecommerce.thecommercetask.domain.user.exception;

import com.thecommerce.thecommercetask.global.exception.GlobalException;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;

/**
 * DuplicatePhoneNumberException : 핸드폰 번호가 중복일 경우 exception 발생
 */
public class DuplicatePhoneNumberException extends GlobalException {
    public DuplicatePhoneNumberException(GlobalErrorCode code) {
        super(code);
    }
}
