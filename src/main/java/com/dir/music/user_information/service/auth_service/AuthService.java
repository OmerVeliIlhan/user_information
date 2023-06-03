package com.dir.music.user_information.service.auth_service;

import com.dir.music.user_information.service.IService;
import com.dir.music.user_information.service.auth_service.input.AuthServiceRegisterInput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceRegisterOutput;
import org.springframework.stereotype.Service;

@Service
public interface AuthService extends IService {
    AuthServiceRegisterOutput register(AuthServiceRegisterInput authServiceRegisterInput);
}
