package com.jxau.exceptions;

public class SysException extends RuntimeException {
    private String message;

    public SysException() {
    }

    public SysException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
