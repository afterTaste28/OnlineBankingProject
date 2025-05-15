package com.aftertaste.onlinebanking.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", HttpStatus.CONFLICT),
    SYSTEM_EXCEPTION("SYSTEM_EXCEPTION",HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    private final HttpStatus httpStatus;

    ErrorCode(String code, HttpStatus httpStatus){
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
