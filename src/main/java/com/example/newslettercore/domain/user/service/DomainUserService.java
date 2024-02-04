package com.example.newslettercore.domain.user.service;

import com.example.newslettercore.domain.exception.CantLoginException;
import com.example.newslettercore.domain.exception.NewsletterCoreObjectNotFoundException;
import com.example.newslettercore.domain.user.model.UserAndTokenDTO;
import com.example.newslettercore.domain.user.model.UserCreateDTO;
import com.example.newslettercore.domain.user.model.UserDTO;
import com.example.newslettercore.domain.user.model.UserLoginDataDTO;
import com.example.newslettercore.domain.user.model.UserUpdateDTO;
import com.example.newslettercore.domain.user.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;

import java.util.Optional;

public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    public DomainUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {

        return userRepository.save(userCreateDTO);
    }

    @Override
    public UserDTO updateUser(String userId, UserUpdateDTO userUpdateDTO) {

        Optional<UserDTO> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NewsletterCoreObjectNotFoundException(UserDTO.class.getSimpleName(), userId);
        }

        UserDTO updatedUserBeforeSave = updateUserData(userOptional.get(), userUpdateDTO);
        return userRepository.save(updatedUserBeforeSave);
    }

    private UserDTO updateUserData(UserDTO userDTO, UserUpdateDTO userUpdateDTO) {

        if (Strings.isNotBlank(userUpdateDTO.getName())) {
            userDTO.setName(userUpdateDTO.getName());
        }

        if (Strings.isNotBlank(userUpdateDTO.getEmail())) {
            userDTO.setEmail(userUpdateDTO.getEmail());
        }

        if (Strings.isNotBlank(userUpdateDTO.getPassword())) {
            userDTO.setPassword(userUpdateDTO.getPassword());
        }

        return userDTO;
    }

    @Override
    public UserAndTokenDTO loginUser(UserLoginDataDTO userLoginDataDTO) {

        Optional<UserDTO> userOptional = userRepository.findByEmail(userLoginDataDTO.getEmail());
        if (userOptional.isEmpty()) {
            throw new CantLoginException();
        }

        UserDTO foundUser = userOptional.get();
        String givenPassword = userLoginDataDTO.getPassword();
        boolean incorrectPassword = !givenPassword.equals(foundUser.getPassword());
        if (incorrectPassword) {
            throw new CantLoginException();
        }

        return new UserAndTokenDTO(foundUser, generateLoginToken());
    }

    private String generateLoginToken() {

        return "TODO";
    }
}
