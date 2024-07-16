package com.dsu.turtlelearnserver.global.exception.handler;

import static com.dsu.turtlelearnserver.global.exception.error_code.CommonErrorCode.*;

import com.dsu.turtlelearnserver.global.exception.error_code.CommonErrorCode;
import com.dsu.turtlelearnserver.global.exception.response.ErrorResponse;
import com.dsu.turtlelearnserver.user.exception.DuplicatedUsernameException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedUsernameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicationEmailException(
        DuplicatedUsernameException e
    ) {
        var errorResponse = createErrorResponse(INVALID_PARAMETER, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private ErrorResponse createErrorResponse(CommonErrorCode errorCode,
        String explain) {
        return ErrorResponse.of(errorCode, explain);
    }
}
