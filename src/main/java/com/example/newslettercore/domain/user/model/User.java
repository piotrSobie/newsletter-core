package com.example.newslettercore.domain.user.model;

import com.example.newslettercore.domain.exception.UserEmailInvalidException;
import com.example.newslettercore.domain.exception.UserNameInvalidException;
import com.example.newslettercore.domain.exception.UserPasswordInvalidException;
import com.example.newslettercore.domain.exception.UserRoleInvalidException;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.function.UnaryOperator;

@Getter
public class User {

    private String id;

    private String name;

    private String password;

    private String email;

    private final Role role;

    public User(String id, String name, String password, String email, Role role) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
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
        validateEmail(email);
        validatePassword(password);
        validateRole(role);
    }

    private void validateName(String name) {

        if (name.length() > UserDataRequirements.MAX_USER_NAME_LENGTH) {
            throw new UserNameInvalidException();
        }
    }

    private void validateEmail(String email) {

        boolean invalidEmail = !email.matches(UserDataRequirements.EMAIL_REGEX);
        if (invalidEmail) {
            throw new UserEmailInvalidException();
        }
    }

    public void validatePassword(String password) {

        if (password.length() > UserDataRequirements.MAX_PASSWORD_LENGTH) {
            throw new UserPasswordInvalidException();
        }

        if (password.length() < UserDataRequirements.MIN_PASSWORD_LENGTH) {
            throw new UserPasswordInvalidException();
        }
    }

    private void validateRole(Role role) {

        if (null == role) {
            throw new UserRoleInvalidException();
        }
    }

    public User updateNotEncodedUserData(String name, String email) {

        if (Strings.isNotBlank(name)) {
            validateName(name);
            this.name = name;
        }

        if (Strings.isNotBlank(email)) {
            validateEmail(email);
            this.email = email;
        }

        return this;
    }

    public User updatePassword(String newPassword, UnaryOperator<String> hashFunction) {

        if (Strings.isBlank(newPassword)) {
            return this;
        }

        password = hashFunction.apply(newPassword);
        return this;
    }

    public void hashPassword(UnaryOperator<String> hashFunction) {

        password = hashFunction.apply(password);
    }
}
