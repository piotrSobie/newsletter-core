package com.example.newslettercore.domain.user.service;

import com.example.newslettercore.domain.user.model.UserAndTokenDTO;
import com.example.newslettercore.domain.user.model.UserCreateDTO;
import com.example.newslettercore.domain.user.model.UserDTO;
import com.example.newslettercore.domain.user.model.UserLoginDataDTO;
import com.example.newslettercore.domain.user.model.UserUpdateDTO;

public interface UserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO updateUser(String userId, UserUpdateDTO userUpdateDTO);

    UserAndTokenDTO loginUser(UserLoginDataDTO userLoginDataDTO);
}
