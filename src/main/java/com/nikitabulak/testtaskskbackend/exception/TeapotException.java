package com.nikitabulak.testtaskskbackend.exception;

public class TeapotException extends RuntimeException {
    public TeapotException() {
        super("I'm a teapot");
    }
}
