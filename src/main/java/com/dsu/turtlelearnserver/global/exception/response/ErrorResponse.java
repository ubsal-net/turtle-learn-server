package com.dsu.turtlelearnserver.global.exception.response;

import com.dsu.turtlelearnserver.global.exception.error_code.CommonErrorCode;
import com.dsu.turtlelearnserver.global.exception.error_code.ErrorCode;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public record ErrorResponse(ErrorCode code, String message, String explain) {

    public static ErrorResponse of(final CommonErrorCode code, final String explain) {
        return new ErrorResponse(code, code.getMessage(), explain);
    }

    public ResponseEntity<ErrorResponse> toResponseEntity() {
        return ResponseEntity.status(code.getHttpStatus()).body(this);
    }
}
