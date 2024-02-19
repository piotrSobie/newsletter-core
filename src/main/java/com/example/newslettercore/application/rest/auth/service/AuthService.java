package com.example.newslettercore.application.rest.auth.service;

import com.example.newslettercore.application.rest.auth.mapper.AuthUserMapper;
import com.example.newslettercore.application.rest.auth.model.AuthResponse;
import com.example.newslettercore.application.rest.auth.model.AuthUser;
import com.example.newslettercore.application.rest.user.mapper.RestUserMapper;
import com.example.newslettercore.application.rest.user.model.UserCreateDTO;
import com.example.newslettercore.application.rest.user.model.UserLoginDataDTO;
import com.example.newslettercore.application.rest.user.model.UserResponse;
import com.example.newslettercore.domain.exception.CantLoginException;
import com.example.newslettercore.domain.user.model.User;
import com.example.newslettercore.domain.user.service.UserService;
import com.example.newslettercore.domain.user.value.UserEmailValue;
import com.example.newslettercore.domain.user.value.UserNameValue;
import com.example.newslettercore.domain.user.value.UserPasswordValue;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserCreateDTO userCreateDTO) {

        String encodedPassword = passwordEncoder.encode(userCreateDTO.getPassword());
        User createdUser = userService.createUser(new UserNameValue(userCreateDTO.getName()), new UserPasswordValue(encodedPassword),
                new UserEmailValue(userCreateDTO.getEmail()), userCreateDTO.getRole());

        String jwtToken = generateToken(createdUser);
        UserResponse userResponse = RestUserMapper.getMapper.userToUserResponse(createdUser);
        return new AuthResponse(userResponse, jwtToken);
    }

    public AuthResponse authenticate(UserLoginDataDTO userLoginDataDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDataDTO.getEmail(), userLoginDataDTO.getPassword())
        );

        User foundUser = userService.findByEmail(userLoginDataDTO.getEmail())
                .orElseThrow(CantLoginException::new);
        String jwtToken = generateToken(foundUser);
        UserResponse userResponse = RestUserMapper.getMapper.userToUserResponse(foundUser);
        return new AuthResponse(userResponse, jwtToken);
    }

    private String generateToken(User user) {

        AuthUser authUser = AuthUserMapper.getMapper.userToAuthUser(user);
        return jwtService.generateToken(authUser);
    }
}
