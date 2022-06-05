package com.epam.esm.exceptions;

public enum ErrorCodeStatus {
    NOT_FOUND(40004), CONFLICT(40009), BAD_REQUEST(40000);

    final int code;

    ErrorCodeStatus(int code) {
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
