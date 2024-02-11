package com.example.newslettercore.domain.exception;

public class NewsletterTagsInvalidException extends RuntimeException {

    private static final String NEWSLETTER_TAGS_INVALID_MSG = "Newsletter tags can't be empty";

    public NewsletterTagsInvalidException() {

        super(NEWSLETTER_TAGS_INVALID_MSG);
    }
}
