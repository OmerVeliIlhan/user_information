package com.dir.music.user_information.service.auth_service;

import com.dir.music.user_information.clients.AuthServiceFeign;
import com.dir.music.user_information.service.auth_service.input.AuthServiceRegisterInput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceRegisterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    final AuthServiceFeign authServiceFeign;

    @Autowired
    public AuthServiceImpl(AuthServiceFeign authServiceFeign) {
        this.authServiceFeign = authServiceFeign;
    }

    @Override
    public AuthServiceRegisterOutput register(AuthServiceRegisterInput authServiceRegisterInput) {
        try {
            return authServiceFeign.register(authServiceRegisterInput).getBody();
        } catch (Exception e) {
            return AuthServiceRegisterOutput.builder().success(false).build();
        }
    }

}
