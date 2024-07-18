package com.dsu.turtlelearnserver.global.exception.handler;

import static com.dsu.turtlelearnserver.global.exception.error_code.CommonErrorCode.*;

import com.dsu.turtlelearnserver.global.exception.error_code.CommonErrorCode;
import com.dsu.turtlelearnserver.global.exception.response.ErrorResponse;
import com.dsu.turtlelearnserver.randomquestion.exception.IllegalQuestionAccessException;
import com.dsu.turtlelearnserver.user.exception.DuplicatedUsernameException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException() {
        var errorResponse = createErrorResponse(RESOURCE_NOT_FOUND, "해당 url은 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(
        NoSuchElementException e) {
        var errorResponse = createErrorResponse(RESOURCE_NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalQuestionAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalQuestionAccessException(
        IllegalQuestionAccessException e
    ) {
        var errorResponse = createErrorResponse(FORBIDDEN, e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleMultipleSelectionException(
        IllegalArgumentException e
    ) {
        var errorResponse = createErrorResponse(INVALID_PARAMETER, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ErrorResponse createErrorResponse(CommonErrorCode errorCode,
        String explain) {
        return ErrorResponse.of(errorCode, explain);
    }
}
