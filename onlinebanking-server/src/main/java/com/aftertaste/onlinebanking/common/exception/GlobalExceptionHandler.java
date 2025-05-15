package com.aftertaste.onlinebanking.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException apiException){
        ErrorResponse errorResponse = new ErrorResponse(apiException.getErrorCode().getCode(), apiException.getMessage());

        return ResponseEntity
                .status(apiException.getErrorCode().getHttpStatus())
                .body(errorResponse);
    }

}
