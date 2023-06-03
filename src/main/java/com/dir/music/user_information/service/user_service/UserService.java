package com.dir.music.user_information.service.user_service;

import com.dir.music.user_information.service.IService;
import com.dir.music.user_information.service.user_service.exception.UserAlreadyExistsException;
import com.dir.music.user_information.service.user_service.exception.UserNotFoundException;
import com.dir.music.user_information.service.user_service.input.UserDeleteInput;
import com.dir.music.user_information.service.user_service.input.UserGetInput;
import com.dir.music.user_information.service.user_service.input.UserRegisterInput;
import com.dir.music.user_information.service.user_service.input.UserUpdateInput;
import com.dir.music.user_information.service.user_service.output.UserProfileDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService {

    UserProfileDTO getUserProfile(UserGetInput userGetInput) throws UserNotFoundException;

    UserProfileDTO createUser(UserRegisterInput userRegisterInput) throws UserAlreadyExistsException;

    UserProfileDTO updateUser(UserUpdateInput userUpdateInput) throws UserNotFoundException, UserAlreadyExistsException;

    void deleteUser(UserDeleteInput userDeleteInput) throws UserNotFoundException;

}
