package com.example.actico.exception;

public class BusinessException extends RuntimeException {

    private ErrorCode code;

    public BusinessException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
}
