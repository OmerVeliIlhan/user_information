package com.dir.music.user_information.clients;


import com.dir.music.user_information.service.auth_service.input.AuthServiceGetClaimsInput;
import com.dir.music.user_information.service.auth_service.input.AuthServiceRegisterInput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceGetClaimsOutput;
import com.dir.music.user_information.service.auth_service.output.AuthServiceRegisterOutput;
import jakarta.validation.Valid;
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

    @PostMapping(path = "/token-validation/get-claims", consumes = "application/json", produces = "application/json")
    ResponseEntity<AuthServiceGetClaimsOutput> getClaims(
            @Valid @RequestBody AuthServiceGetClaimsInput tokenValidationControllerInput
    );


}
