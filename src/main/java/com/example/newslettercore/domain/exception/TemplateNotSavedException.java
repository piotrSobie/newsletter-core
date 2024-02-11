package com.example.newslettercore.domain.exception;

public class TemplateNotSavedException extends RuntimeException {

    private static final String TEMPLATE_NOTE_SAVED_MSG = "Error during template saving.";

    public TemplateNotSavedException() {

        super(TEMPLATE_NOTE_SAVED_MSG);
    }
}
