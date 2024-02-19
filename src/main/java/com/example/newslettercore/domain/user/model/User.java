package com.example.newslettercore.domain.user.model;

import com.example.newslettercore.domain.exception.UserEmailInvalidException;
import com.example.newslettercore.domain.exception.UserNameInvalidException;
import com.example.newslettercore.domain.exception.UserPasswordInvalidException;
import com.example.newslettercore.domain.exception.UserRoleInvalidException;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class User {

    private String id;

    private String name;

    private String password;

    private String email;

    private final Role role;

    public User(String id, String name, String password, String email, Role role) {

        this(name, password, email, role);
        this.id = id;
    }

    public User(String name, String password, String email, Role role) {

        validateUserData(name, password, email, role);

        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    private void validateUserData(String name, String password, String email, Role role) {

        validateName(name);
//        validatePassword(password);
// todo - I have encoded password here. Password encoder is defined in application.rest.auth.configuration.ApplicationConfiguration. How can I handle
//  password validation in rich object?
        validateEmail(email);
        validateRole(role);
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

    private void validateRole(Role role) {

        if (null == role) {
            throw new UserRoleInvalidException();
        }
    }

    public User updateUser(String name, String password, String email) {

        if (Strings.isNotBlank(name)) {
            validateName(name);
            this.name = name;
        }

        if (Strings.isNotBlank(password)) {
//            validatePassword(password); // todo the same as in validateUserData
            this.password = password;
        }

        if (Strings.isNotBlank(email)) {
            validateEmail(email);
            this.email = email;
        }

        return this;
    }
}
