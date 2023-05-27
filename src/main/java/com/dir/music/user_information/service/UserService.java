package com.dir.music.user_information.service;

import com.dir.music.user_information.dto.UserProfileDTO;
import com.dir.music.user_information.model.User;
import com.dir.music.user_information.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User userModel) {
        return userRepository.save(userModel);
    }

    public User updateUser(User userModel) {

        return userRepository.save(userModel);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserProfileDTO getUserProfile(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserProfileDTO userProfileDTO = new UserProfileDTO();
            userProfileDTO.setUserName(user.getUserName());
            userProfileDTO.setUserSurname(user.getUserSurname());
            userProfileDTO.setFollowerCount(user.getFollowerCount());

            return userProfileDTO;
        } else {
            throw new RuntimeException("User not found for id: " + userId);
        }
    }

    public User updateUserProfile(Long userId, User newUserData) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found for id: " + userId));

        user.setUserName(newUserData.getUserName());
        user.setUserSurname(newUserData.getUserSurname());
        user.setEmail(newUserData.getEmail());
        user.setPhoneNumber(newUserData.getPhoneNumber());
        user.setDateOfBirth(newUserData.getDateOfBirth());

        userRepository.save(user);
        return user;
    }
}
