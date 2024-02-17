package com.example.newslettercore.domain.exception;

public class NewsletterCronSendingFrequencyInvalidException extends RuntimeException {

    private static final String NEWSLETTER_CRON_INVALID_MSG = "Newsletter cronSendingFrequency can't be empty";

    public NewsletterCronSendingFrequencyInvalidException() {

        super(NEWSLETTER_CRON_INVALID_MSG);
    }
}
