package com.example.phonebook.exceptions;

public class FileNotUploadedException extends ControllerException {

    private static final String DEFAULT_MESSAGE = "File not uploaded";
    private static final String DEFAULT_DETAILS = "This file can not uploaded. Check file format or size.";

    public FileNotUploadedException() {
        super(DEFAULT_MESSAGE, DEFAULT_DETAILS);
    }
}
