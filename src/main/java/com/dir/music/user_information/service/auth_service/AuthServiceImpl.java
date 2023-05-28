package com.dir.music.user_information.service.auth_service;

import com.dir.music.user_information.clients.AuthServiceFeign;
import com.dir.music.user_information.service.auth_service.exceptions.ExpiredTokenException;
import com.dir.music.user_information.service.auth_service.exceptions.InvalidTokenException;
import com.dir.music.user_information.service.auth_service.input.AuthServiceGetClaimsInput;
import com.dir.music.user_information.service.auth_service.input.AuthServiceRegisterInput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceGetClaimsOutput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceRegisterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public AuthServiceGetClaimsOutput getClaims(AuthServiceGetClaimsInput authServiceGetClaimsInput)
            throws InvalidTokenException, ExpiredTokenException {

        final ResponseEntity<AuthServiceGetClaimsOutput> getClaimsOutputResponseEntity =
                authServiceFeign.getClaims(authServiceGetClaimsInput);

        if (getClaimsOutputResponseEntity.getStatusCode().is2xxSuccessful()) {
            return getClaimsOutputResponseEntity.getBody();
        } else if (getClaimsOutputResponseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new ExpiredTokenException();
        } else {
            throw new InvalidTokenException();
        }
    }
}
