package com.example.newslettercore.domain.exception;

public class TemplateTextInvalidException extends RuntimeException {

    private static final String TEMPLATE_TEXT_INVALID_MSG = "Template text can't be empty";

    public TemplateTextInvalidException() {

        super(TEMPLATE_TEXT_INVALID_MSG);
    }
}
