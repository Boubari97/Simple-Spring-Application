package com.example.phonebook.exceptions;

public class ControllerException extends RuntimeException {

    private final String message;
    private final String details;

    public ControllerException(String message) {
        this.message = message;
        this.details = "Details are missing.";
    }

    public ControllerException(String message, String details) {
        this.message = message;
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
