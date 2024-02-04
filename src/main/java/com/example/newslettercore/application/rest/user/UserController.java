package com.example.newslettercore.application.rest.user;

import com.example.newslettercore.domain.user.model.UserAndTokenDTO;
import com.example.newslettercore.domain.user.model.UserCreateDTO;
import com.example.newslettercore.domain.user.model.UserDTO;
import com.example.newslettercore.domain.user.model.UserLoginDataDTO;
import com.example.newslettercore.domain.user.model.UserUpdateDTO;
import com.example.newslettercore.domain.user.service.UserService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserController.CONTROLLER_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class UserController {

    public static final String CONTROLLER_ENDPOINT = "/api/v1";
    public static final String USER_ENDPOINT = "/user";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping(path = USER_ENDPOINT + "/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {

        UserDTO userDTO = userService.createUser(userCreateDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping(path = USER_ENDPOINT + "/login")
    public ResponseEntity<UserAndTokenDTO> loginUser(@RequestBody UserLoginDataDTO userLoginDataDTO) {

        UserAndTokenDTO userAndTokenDTO = userService.loginUser(userLoginDataDTO);
        return new ResponseEntity<>(userAndTokenDTO, HttpStatus.OK);
    }

    @PatchMapping(path = USER_ENDPOINT + "/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Nonnull @PathVariable("userId") String userId, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {

        UserDTO userDTO = userService.updateUser(userId, userUpdateDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
