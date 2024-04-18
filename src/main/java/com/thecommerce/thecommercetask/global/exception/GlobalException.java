package com.thecommerce.thecommercetask.global.exception;

import com.thecommerce.thecommercetask.global.exception.error.GlobalError;
import com.thecommerce.thecommercetask.global.exception.error.GlobalErrorCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException{

    private final GlobalError error;

    public GlobalException(GlobalErrorCode code) {
        super(code.getMessage());
        this.error = GlobalError.of(code);
    }

    public GlobalException(GlobalErrorCode code, String message) {
        super(code.getMessage());
        this.error = GlobalError.of(code, message);
    }
}
