package com.study.todoparty.config.exception.customException;

public class TodoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4937536120992271478L;

    public TodoNotFoundException() {
    }

    public TodoNotFoundException(String message) {
        super(message);
    }

    public TodoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoNotFoundException(Throwable cause) {
        super(cause);
    }

    public TodoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
