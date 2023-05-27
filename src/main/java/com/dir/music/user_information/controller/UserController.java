package com.dir.music.user_information.controller;

import com.dir.music.user_information.dto.UserProfileDTO;
import com.dir.music.user_information.model.User;
import com.dir.music.user_information.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User userModel) {
        return userService.createUser(userModel);
    }

    @PutMapping(path = "{userId}")
    public User updateUser(@PathVariable("userId") Long id, @RequestBody User userModel) {
        userModel.setId(id);
        return userService.updateUser(userModel);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserProfileDTO getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }

    @PutMapping("/{id}")
    public User updateUserProfile(@PathVariable Long id, @RequestBody User newUserData) {
        return userService.updateUserProfile(id, newUserData);
    }

    /*
    @Autowired
    private AuthService authService;

    @PutMapping(path = "/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable("userId") Long id, @RequestBody User user, @RequestHeader("Authorization") String token) {
        try {
            boolean isTokenValid = authService.verifyToken(token);
            if (isTokenValid) {
                user.setId(id);
                return ResponseEntity.ok(userService.updateUserProfile(id, newUserData););
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (UnambiguousException | TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    */
}
