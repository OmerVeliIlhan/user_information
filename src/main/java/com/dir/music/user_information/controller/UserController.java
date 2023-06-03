package com.dir.music.user_information.controller;

import com.dir.music.user_information.service.user_service.UserServiceImpl;
import com.dir.music.user_information.service.user_service.exception.UserAlreadyExistsException;
import com.dir.music.user_information.service.user_service.exception.UserNotFoundException;
import com.dir.music.user_information.service.user_service.input.UserDeleteInput;
import com.dir.music.user_information.service.user_service.input.UserGetInput;
import com.dir.music.user_information.service.user_service.input.UserRegisterInput;
import com.dir.music.user_information.service.user_service.input.UserUpdateInput;
import com.dir.music.user_information.service.user_service.output.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/{userName}")
    public ResponseEntity<UserProfileDTO> getUserById(
            @PathVariable("userName") String userName,
            @RequestParam(value = "isDetailed", defaultValue = "false") boolean isDetailed
    ) {
        try {
            final UserProfileDTO userProfileDTO = userService.getUserProfile(UserGetInput.builder()
                    .username(userName).isDetailed(isDetailed).build());
            return ResponseEntity.ok(userProfileDTO);
        } catch (UserNotFoundException e) {
            return handleException(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileDTO> createUser(@RequestBody UserRegisterInput userModel) {
        try {
            final UserProfileDTO userProfileDTO = userService.createUser(userModel);
            return ResponseEntity.ok(userProfileDTO);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/{userName}")
    public ResponseEntity<UserProfileDTO> updateUser
            (@RequestBody UserRegisterInput userRegisterInput,
             @PathVariable("userName") String userName,
             @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            final UserProfileDTO userProfileDTO = userService.updateUser(UserUpdateInput.builder()
                    .userName(userName)
                    .phoneNumber(userRegisterInput.getPhoneNumber())
                    .dateOfBirth(userRegisterInput.getDateOfBirth())
                    .token(token)
                    .build());
            return ResponseEntity.ok(userProfileDTO);
        } catch (UserAlreadyExistsException | UserNotFoundException e) {
            return handleException(e);
        }
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<Object> deleteUser(
            @PathVariable("username") String userName,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {

            userService.deleteUser(UserDeleteInput.builder().
                    userName(userName)
                    .token(token)
                    .build());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UserNotFoundException e) {
            return handleException(e);
        }
    }

    private <T> ResponseEntity<T> handleException(Exception e) {
        if (e instanceof UserAlreadyExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (e instanceof UserNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
