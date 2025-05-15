package com.aftertaste.onlinebanking.common.exception;

public class ErrorResponse {
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    String errorCode;
    String errorString;

    public ErrorResponse(String errorCode, String errorString){
        this.errorCode = errorCode;
        this.errorString = errorString;
    }
    public ErrorResponse(){}
}
