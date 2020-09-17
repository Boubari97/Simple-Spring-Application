package com.example.phonebook.exceptions;

public class RegistrationFailedException extends ControllerException {

    private static final String DEFAULT_MESSAGE = "Registration aborted!";
    private static final String DEFAULT_DETAILS = "Can not create new user. Check parameters from input labels.";

    public RegistrationFailedException() {
        super(DEFAULT_MESSAGE, DEFAULT_DETAILS);
    }

    public RegistrationFailedException(String details) {
        super(DEFAULT_MESSAGE, details);
    }
}
