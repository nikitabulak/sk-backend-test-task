package com.nikitabulak.testtaskskbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final RuntimeException e) {
        return new ErrorResponse(
                e.getMessage()
        );
    }

    @ExceptionHandler({TeapotException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorResponse handleNotAvailableException(final RuntimeException e) {
        return new ErrorResponse(
                e.getMessage()
        );
    }
}

