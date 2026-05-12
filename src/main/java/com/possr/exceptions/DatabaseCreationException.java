package com.possr.exceptions;

public class DatabaseCreationException extends RuntimeException {

    public DatabaseCreationException(String message) {
        super(message);
    }

    public DatabaseCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
