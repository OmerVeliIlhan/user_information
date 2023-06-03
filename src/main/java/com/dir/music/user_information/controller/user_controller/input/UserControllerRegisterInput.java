package com.dir.music.user_information.controller.user_controller.input;

import com.dir.music.user_information.service.IServiceInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserControllerRegisterInput implements IServiceInput {
    private String userName;
    private String phoneNumber;
    private String password;
    private Date dateOfBirth;
}
