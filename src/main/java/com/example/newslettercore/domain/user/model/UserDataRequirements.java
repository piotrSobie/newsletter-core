package com.example.newslettercore.domain.user.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDataRequirements {

    public static final int MAX_USER_NAME_LENGTH = 10;

    public static final int MAX_PASSWORD_LENGTH = 30;
    public static final int MIN_PASSWORD_LENGTH = 5;

    public static final String EMAIL_REGEX = "^(.+)@(\\S+)$";
}
