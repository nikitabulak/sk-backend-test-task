package com.nikitabulak.testtaskskbackend.exception;

public class MissingIdException extends RuntimeException{
    public MissingIdException() {
        super("Нет записи с таким id");
    }
}
