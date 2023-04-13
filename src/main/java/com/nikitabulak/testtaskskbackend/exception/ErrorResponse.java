package com.nikitabulak.testtaskskbackend.exception;

public class ErrorResponse {
    private final String error;
    private final Throwable cause;

    public ErrorResponse(String error) {
        this.error = error;
        this.cause = null;
    }
    public ErrorResponse(String error, Throwable cause) {
        this.error = error;
        this.cause = cause;
    }

    public String getError() {
        return error;
    }
    public Throwable getCause() {
        return cause;
    }
}
