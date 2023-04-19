package com.nikitabulak.testtaskskbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final RuntimeException e) {
        return new ErrorResponse(e.getMessage(), e);
    }

    @ExceptionHandler(TeapotException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorResponse handleNotAvailableException(final TeapotException e) {
        return new ErrorResponse(e.getMessage(), e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadArgumentException(final MethodArgumentNotValidException e) {
        return new ErrorResponse(e.getMessage());
    }
}

