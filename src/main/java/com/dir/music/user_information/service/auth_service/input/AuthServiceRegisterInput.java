package com.dir.music.user_information.service.auth_service.input;

import com.dir.music.user_information.service.IServiceInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthServiceRegisterInput implements IServiceInput {
    private String username;
    private String password;
}
