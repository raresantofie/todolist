package com.todlist.todlist.exceptions;

public class UserNotFoundException  extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
