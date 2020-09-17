package com.example.phonebook.exceptions;

public class UserNotFoundException extends ControllerException {

    private static final String MESSAGE = "User not found!";
    private static final String DETAILS = "Can not find user with this parameters!";

    public UserNotFoundException() {
        super(MESSAGE, DETAILS);
    }
}
