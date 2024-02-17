package com.example.newslettercore.domain.exception;

public class TemplateCanalsNotFoundException extends RuntimeException {

    private static final String TEMPLATE_CANALS_INVALID_MSG = "Template canals can't be empty.";

    public TemplateCanalsNotFoundException() {

        super(TEMPLATE_CANALS_INVALID_MSG);
    }
}
