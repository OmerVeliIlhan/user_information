package com.dir.music.user_information.controller.user_controller.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserControllerUserProfileOutput {
    private String userName;
    private Integer followerCount;
    private boolean includePrivateInformation;
    private Date dateOfBirth;
    private String phoneNumber;
}
