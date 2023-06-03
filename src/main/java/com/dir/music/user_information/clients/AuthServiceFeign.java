package com.dir.music.user_information.clients;


import com.dir.music.user_information.service.auth_service.input.AuthServiceRegisterInput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceRegisterOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("dir-authentication-service")
public interface AuthServiceFeign {
    @PostMapping("/register")
    ResponseEntity<AuthServiceRegisterOutput> register(
            @RequestBody AuthServiceRegisterInput authControllerRegisterInput
    );

}
