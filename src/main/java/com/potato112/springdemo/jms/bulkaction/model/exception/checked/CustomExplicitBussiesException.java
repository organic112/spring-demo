package com.potato112.springdemo.jms.bulkaction.model.exception.checked;

/**
 * Exception for explicit business rule violation
 */
public class CustomExplicitBussiesException extends Exception {

    public CustomExplicitBussiesException(String message) {
        super(message);
    }

    public CustomExplicitBussiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
