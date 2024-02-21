package com.example.newslettercore.domain.user.model;

import com.example.newslettercore.domain.exception.UserEmailInvalidException;
import com.example.newslettercore.domain.exception.UserNameInvalidException;
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

    public User(String id, String name, String hashedPassword, String email, Role role) {

        this(name, hashedPassword, email, role);
        this.id = id;
    }

    public User(String name, String hashedPassword, String email, Role role) {

        validateUserData(name, email, role);

        this.name = name;
        this.password = hashedPassword;
        this.email = email;
        this.role = role;
    }

    private void validateUserData(String name, String email, Role role) {

        validateName(name);
        validateEmail(email);
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

    private void validateRole(Role role) {

        if (null == role) {
            throw new UserRoleInvalidException();
        }
    }

    public User updateUser(String name, String hashedPassword, String email) {

        if (Strings.isNotBlank(name)) {
            validateName(name);
            this.name = name;
        }

        if (Strings.isNotBlank(hashedPassword)) {
            this.password = hashedPassword;
        }

        if (Strings.isNotBlank(email)) {
            validateEmail(email);
            this.email = email;
        }

        return this;
    }
}
