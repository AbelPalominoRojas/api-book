package com.ironman.book.exception;

public abstract class AppBaseException extends RuntimeException {
    public AppBaseException(String message) {
        super(message);
    }
}
