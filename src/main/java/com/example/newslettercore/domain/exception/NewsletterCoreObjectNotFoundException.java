package com.example.newslettercore.domain.exception;

import java.text.MessageFormat;

public class NewsletterCoreObjectNotFoundException extends RuntimeException {

    private static final String ENTITY_NOT_FOUND_MSG = "Object \"{0}\" with id \"{1}\" not found.";

    public NewsletterCoreObjectNotFoundException(String objectName, String id) {

        super(MessageFormat.format(ENTITY_NOT_FOUND_MSG, objectName, id));
    }
}
