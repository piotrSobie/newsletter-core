package com.example.newslettercore.application.rest.user.api;

import com.example.newslettercore.application.rest.user.mapper.RestUserMapper;
import com.example.newslettercore.application.rest.user.model.UserResponse;
import com.example.newslettercore.application.rest.user.model.UserUpdateDTO;
import com.example.newslettercore.domain.user.model.User;
import com.example.newslettercore.domain.user.service.UserService;
import com.example.newslettercore.domain.user.value.UserEmailValue;
import com.example.newslettercore.domain.user.value.UserNameValue;
import com.example.newslettercore.domain.user.value.UserPasswordValue;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PatchMapping(path = USER_ENDPOINT + "/{userId}")
    public ResponseEntity<UserResponse> updateUser(@Nonnull @PathVariable("userId") String userId, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {

        User updatedUser = userService.updateUser(userId, new UserNameValue(userUpdateDTO.getName()), new UserPasswordValue(userUpdateDTO.getPassword()),
                new UserEmailValue(userUpdateDTO.getEmail()));
        UserResponse responseUser = RestUserMapper.getMapper.userToUserResponse(updatedUser);
        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }
}
