package com.example.newslettercore.domain.exception;

public class UserRoleInvalidException extends RuntimeException {

    private static final String USER_ROLE_INVALID_MSG = "User role can't be null";

    public UserRoleInvalidException() {

        super(USER_ROLE_INVALID_MSG);
    }
}
