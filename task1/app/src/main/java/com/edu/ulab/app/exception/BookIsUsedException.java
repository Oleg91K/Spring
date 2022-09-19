package com.edu.ulab.app.exception;

public class BookIsUsedException extends RuntimeException{
    public BookIsUsedException(String message) {
        super(message);
    }
}
