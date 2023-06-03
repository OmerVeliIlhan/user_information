package com.dir.music.user_information.controller.user_controller;

import com.dir.music.user_information.controller.user_controller.input.UserControllerRegisterInput;
import com.dir.music.user_information.controller.user_controller.input.UserControllerUpdateInput;
import com.dir.music.user_information.controller.user_controller.output.UserControllerUserProfileOutput;
import com.dir.music.user_information.service.user_service.UserServiceImpl;
import com.dir.music.user_information.service.user_service.exception.UserAlreadyExistsException;
import com.dir.music.user_information.service.user_service.exception.UserNotFoundException;
import com.dir.music.user_information.service.user_service.input.UserDeleteInput;
import com.dir.music.user_information.service.user_service.input.UserGetInput;
import com.dir.music.user_information.service.user_service.input.UserRegisterInput;
import com.dir.music.user_information.service.user_service.input.UserUpdateInput;
import com.dir.music.user_information.service.user_service.output.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserControllerUserProfileOutput> getUserById(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "isDetailed", defaultValue = "false") boolean isDetailed
    ) {
        try {
            final UserProfileDTO userProfileDTO = userService.getUserProfile(UserGetInput.builder()
                    .userId(userId).isDetailed(isDetailed).build());
            final UserControllerUserProfileOutput userControllerUserProfileOutput
                    = UserControllerUserProfileOutput.builder()
                    .userName(userProfileDTO.getUserName())
                    .phoneNumber(userProfileDTO.getPhoneNumber())
                    .dateOfBirth(userProfileDTO.getDateOfBirth())
                    .build();
            return ResponseEntity.ok(userControllerUserProfileOutput);
        } catch (UserNotFoundException e) {
            return handleException(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserControllerUserProfileOutput> createUser(@RequestBody UserControllerRegisterInput userModel) {
        try {
            final UserRegisterInput userRegisterInput = UserRegisterInput.builder()
                    .userName(userModel.getUserName())
                    .phoneNumber(userModel.getPhoneNumber())
                    .dateOfBirth(userModel.getDateOfBirth())
                    .password(userModel.getPassword())
                    .build();
            final UserProfileDTO userProfileDTO = userService.createUser(userRegisterInput);
            final UserControllerUserProfileOutput userControllerUserProfileOutput
                    = UserControllerUserProfileOutput.builder()
                    .userName(userProfileDTO.getUserName())
                    .phoneNumber(userProfileDTO.getPhoneNumber())
                    .dateOfBirth(userProfileDTO.getDateOfBirth())
                    .build();
            return ResponseEntity.ok(userControllerUserProfileOutput);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserControllerUserProfileOutput> updateUser
            (@RequestBody UserControllerUpdateInput userControllerUpdateInput,
             @PathVariable("userId") Long userId) {
        try {
            final UserUpdateInput userUpdateInput = UserUpdateInput.builder()
                    .userId(userId)
                    .userName(userControllerUpdateInput.getUserName())
                    .phoneNumber(userControllerUpdateInput.getPhoneNumber())
                    .dateOfBirth(userControllerUpdateInput.getDateOfBirth())
                    .build();
            final UserProfileDTO userProfileDTO = userService.updateUser(userUpdateInput);
            final UserControllerUserProfileOutput userControllerUserProfileOutput =
                    UserControllerUserProfileOutput.builder()
                            .userName(userProfileDTO.getUserName())
                            .phoneNumber(userProfileDTO.getPhoneNumber())
                            .dateOfBirth(userProfileDTO.getDateOfBirth())
                            .build();
            return ResponseEntity.ok(userControllerUserProfileOutput);
        } catch (UserAlreadyExistsException | UserNotFoundException e) {
            return handleException(e);
        }
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Object> deleteUser(
            @PathVariable("userId") Long userId) {
        try {
            userService.deleteUser(UserDeleteInput.builder().
                    userId(userId)
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
