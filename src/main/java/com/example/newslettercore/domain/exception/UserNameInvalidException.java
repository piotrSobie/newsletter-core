package com.example.newslettercore.domain.exception;

import com.example.newslettercore.domain.user.model.UserDataRequirements;

public class UserNameInvalidException extends RuntimeException {

    private static final String USER_NAME_INVALID_MSG = "User name is invalid. Max length should be " + UserDataRequirements.MAX_USER_NAME_LENGTH;

    public UserNameInvalidException() {

        super(USER_NAME_INVALID_MSG);
    }
}
