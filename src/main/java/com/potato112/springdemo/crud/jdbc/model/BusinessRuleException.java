package com.potato112.springdemo.crud.jdbc.model;


/**
 * Represents some business rule exception.
 */
public class BusinessRuleException extends Exception {


    public BusinessRuleException(String message) {
        super(message);
    }
}
