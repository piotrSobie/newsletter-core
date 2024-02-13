package com.example.newslettercore.domain.user.service;

import com.example.newslettercore.domain.exception.CantLoginException;
import com.example.newslettercore.domain.exception.NewsletterCoreObjectNotFoundException;
import com.example.newslettercore.domain.user.model.User;
import com.example.newslettercore.domain.user.repository.UserRepository;
import com.example.newslettercore.domain.user.value.UserEmailValue;
import com.example.newslettercore.domain.user.value.UserNameValue;
import com.example.newslettercore.domain.user.value.UserPasswordValue;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User createUser(UserNameValue name, UserPasswordValue password, UserEmailValue email) {

        User userToCreate = new User(name.getValue(), password.getValue(), email.getValue());
        return userRepository.save(userToCreate);
    }

    public User updateUser(String userId, UserNameValue name, UserPasswordValue password, UserEmailValue email) {

        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new NewsletterCoreObjectNotFoundException(User.class.getSimpleName(), userId));
        User updatedUser = foundUser.updateUser(name.getValue(), password.getValue(), email.getValue());
        return userRepository.save(updatedUser);
    }

    public User loginUser(UserEmailValue email, UserPasswordValue givenPassword) {

        User foundUser = userRepository.findByEmail(email.getValue())
                .orElseThrow(CantLoginException::new);
        return foundUser.loginUser(givenPassword.getValue());
    }
}
