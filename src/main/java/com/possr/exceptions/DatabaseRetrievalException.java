package com.possr.exceptions;

public class DatabaseRetrievalException extends RuntimeException {

    public DatabaseRetrievalException(String message) {
        super(message);
    }

    public DatabaseRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
