package com.thecommerce.thecommercetask.global.exception;

import com.thecommerce.thecommercetask.global.exception.error.ErrorResponse;
import com.thecommerce.thecommercetask.global.exception.error.GlobalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String CHECK_DUPLICATION = "회원Id, 이메일, 핸드폰 번호 중 하나가 중복상태입니다.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
        log.error("{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .code(500)
                .codeString("INTERNAL_ERROR")
                .message("서버 에러, 관리자에게 문의하세요")
                .build();

        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> globalException(GlobalException ex) {
        GlobalError error = ex.getError();

        log.error("message = {} | errorType = {}", error.getMessage(), error.getCode().getType().name());

        ErrorResponse response = ErrorResponse.builder()
                .code(error.getCode().getCode())
                .codeString(error.getCode().name())
                .message(error.getMessage())
                .build();

        return ResponseEntity.status(error.getCode().getCode()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        String errorMessage = fieldErrors.stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("유효하지 않는 입력 값입니다.");

        ErrorResponse response = ErrorResponse.builder()
                .code(BAD_REQUEST.value())
                .codeString(BAD_REQUEST.name())
                .message(errorMessage)
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(DataIntegrityViolationException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .code(CONFLICT.value())
                .codeString(CONFLICT.name())
                .message(CHECK_DUPLICATION)
                .build();

        return ResponseEntity.status(CONFLICT).body(response);
    }
}
