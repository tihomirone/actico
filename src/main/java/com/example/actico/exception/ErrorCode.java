package com.example.actico.exception;

public enum ErrorCode {

    DATA_MISSING_OR_EMPTY(100), NOT_VALID_DATA(200);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
