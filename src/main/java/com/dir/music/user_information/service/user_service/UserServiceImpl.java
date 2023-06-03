package com.dir.music.user_information.service.user_service;

import com.dir.music.user_information.model.User;
import com.dir.music.user_information.repository.UserRepository;
import com.dir.music.user_information.service.auth_service.AuthService;
import com.dir.music.user_information.service.auth_service.input.AuthServiceRegisterInput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceRegisterOutput;
import com.dir.music.user_information.service.user_service.exception.UserAlreadyExistsException;
import com.dir.music.user_information.service.user_service.exception.UserNotFoundException;
import com.dir.music.user_information.service.user_service.input.UserDeleteInput;
import com.dir.music.user_information.service.user_service.input.UserGetInput;
import com.dir.music.user_information.service.user_service.input.UserRegisterInput;
import com.dir.music.user_information.service.user_service.input.UserUpdateInput;
import com.dir.music.user_information.service.user_service.output.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }


    @Override
    public UserProfileDTO getUserProfile(UserGetInput userGetInput)
            throws UserNotFoundException {

        final User user = userRepository.findUserById(userGetInput.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if (userGetInput.isDetailed()) {
            return UserProfileDTO.builder()
                    .userName(user.getUserName())
                    .followerCount(user.getFollowerCount())
                    .phoneNumber(user.getPhoneNumber())
                    .dateOfBirth(user.getDateOfBirth())
                    .includePrivateInformation(true)
                    .build();
        }

        return UserProfileDTO.builder()
                .userName(user.getUserName())
                .followerCount(user.getFollowerCount())
                .includePrivateInformation(false)
                .build();
    }

    @Override
    public UserProfileDTO createUser(UserRegisterInput userRegisterInput) throws UserAlreadyExistsException {
        final AuthServiceRegisterOutput registerOutput = authService
                .register(AuthServiceRegisterInput.builder().
                        username(userRegisterInput.getUserName())
                        .password(userRegisterInput.getPassword()).build());

        if (registerOutput.isSuccess()) {
            final User user = User.builder()
                    .id(registerOutput.getId())
                    .userName(userRegisterInput.getUserName())
                    .followerCount(0)
                    .dateOfBirth(userRegisterInput.getDateOfBirth())
                    .phoneNumber(userRegisterInput.getPhoneNumber())
                    .build();
            userRepository.save(user);
            return UserProfileDTO.builder()
                    .userName(user.getUserName())
                    .build();
        }

        throw new UserAlreadyExistsException();
    }

    @Override
    public UserProfileDTO updateUser(UserUpdateInput userUpdateInput) throws UserNotFoundException, UserAlreadyExistsException {

        final User user = userRepository.findUserById(userUpdateInput.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if (userUpdateInput.getUserName() != null) {
            user.setUserName(userUpdateInput.getUserName());
        }

        if (userUpdateInput.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateInput.getPhoneNumber());
        }

        if (userUpdateInput.getDateOfBirth() != null) {
            user.setDateOfBirth(userUpdateInput.getDateOfBirth());
        }

        userRepository.save(user);
        return UserProfileDTO.builder()
                .userName(user.getUserName())
                .followerCount(user.getFollowerCount())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .includePrivateInformation(true)
                .build();
    }

    @Override
    public void deleteUser(UserDeleteInput userDeleteInput)
            throws UserNotFoundException {

        final User user = userRepository.findUserById(userDeleteInput.getUserId())
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }


}
