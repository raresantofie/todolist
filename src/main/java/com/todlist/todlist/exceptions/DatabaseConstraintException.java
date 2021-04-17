package com.todlist.todlist.exceptions;

public class DatabaseConstraintException extends RuntimeException{

    public DatabaseConstraintException(String message) {
        super(message);
    }
}
