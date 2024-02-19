package com.example.newslettercore.application.rest.auth.api;

import com.example.newslettercore.application.rest.auth.model.AuthResponse;
import com.example.newslettercore.application.rest.auth.service.AuthService;
import com.example.newslettercore.application.rest.user.model.UserCreateDTO;
import com.example.newslettercore.application.rest.user.model.UserLoginDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AuthController.CONTROLLER_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AuthController {

    public static final String CONTROLLER_ENDPOINT = "/api/v1";
    public static final String AUTH_ENDPOINT = "/auth";

    private final AuthService authService;

    @PostMapping(path = AUTH_ENDPOINT + "/register")
    public ResponseEntity<AuthResponse> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {

        AuthResponse authResponse = authService.register(userCreateDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping(path = AUTH_ENDPOINT + "/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserLoginDataDTO userLoginDataDTO) {

        return ResponseEntity.ok(authService.authenticate(userLoginDataDTO));
    }
}
