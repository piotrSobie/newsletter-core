package com.example.newslettercore.domain.exception;

import com.example.newslettercore.domain.user.model.UserDataRequirements;

public class UserPasswordInvalidException extends RuntimeException {

    private static final String USER_PASSWORD_INVALID_MSG =
            "User password is invalid. Length should be between " + UserDataRequirements.MIN_PASSWORD_LENGTH + " and " + UserDataRequirements.MAX_PASSWORD_LENGTH;

    public UserPasswordInvalidException() {

        super(USER_PASSWORD_INVALID_MSG);
    }
}
