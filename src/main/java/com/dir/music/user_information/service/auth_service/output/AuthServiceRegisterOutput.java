package com.dir.music.user_information.service.auth_service.output;

import com.dir.music.user_information.service.IServiceOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthServiceRegisterOutput implements IServiceOutput{
    private boolean success;
    private long id;
    private String username;
}
