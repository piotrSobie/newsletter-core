package com.example.newslettercore.domain.exception;

public class CantLoginException extends RuntimeException {

    private static final String CANT_LOGIN_MSG = "Given email and password combination can't be used to login.";

    public CantLoginException() {

        super(CANT_LOGIN_MSG);
    }
}
