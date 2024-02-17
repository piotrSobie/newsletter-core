package com.example.newslettercore.domain.user.model;

import com.example.newslettercore.domain.exception.CantLoginException;
import com.example.newslettercore.domain.exception.UserEmailInvalidException;
import com.example.newslettercore.domain.exception.UserNameInvalidException;
import com.example.newslettercore.domain.exception.UserPasswordInvalidException;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class User {

    private String id;

    private String name;

    private String password;

    private String email;

    public User(String id, String name, String password, String email) {

        this(name, password, email);
        this.id = id;
    }

    public User(String name, String password, String email) {

        validateUserData(name, password, email);

        this.name = name;
        this.password = password;
        this.email = email;
    }

    private void validateUserData(String name, String password, String email) {

        validateName(name);
        validatePassword(password);
        validateEmail(email);
    }

    private void validateName(String name) {

        if (name.length() > UserDataRequirements.MAX_USER_NAME_LENGTH) {
            throw new UserNameInvalidException();
        }
    }

    private void validatePassword(String password) {

        if (password.length() > UserDataRequirements.MAX_PASSWORD_LENGTH) {
            throw new UserPasswordInvalidException();
        }

        if (password.length() < UserDataRequirements.MIN_PASSWORD_LENGTH) {
            throw new UserPasswordInvalidException();
        }
    }

    private void validateEmail(String email) {

        boolean invalidEmail = !email.matches(UserDataRequirements.EMAIL_REGEX);
        if (invalidEmail) {
            throw new UserEmailInvalidException();
        }
    }

    public User loginUser(String givenPassword) {

        boolean incorrectPassword = !password.equals(givenPassword);
        if (incorrectPassword) {
            throw new CantLoginException();
        }

        return this;
    }

    public User updateUser(String name, String password, String email) {

        if (Strings.isNotBlank(name)) {
            validateName(name);
            this.name = name;
        }

        if (Strings.isNotBlank(password)) {
            validatePassword(password);
            this.password = password;
        }

        if (Strings.isNotBlank(email)) {
            validateEmail(email);
            this.email = email;
        }

        return this;
    }
}
