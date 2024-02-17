package com.example.newslettercore.domain.exception;

import com.example.newslettercore.domain.user.model.UserDataRequirements;

public class UserEmailInvalidException extends RuntimeException {

    private static final String USER_EMAIL_INVALID_MSG = "User email is invalid. It does not pass regex " + UserDataRequirements.EMAIL_REGEX;

    public UserEmailInvalidException() {

        super(USER_EMAIL_INVALID_MSG);
    }
}
