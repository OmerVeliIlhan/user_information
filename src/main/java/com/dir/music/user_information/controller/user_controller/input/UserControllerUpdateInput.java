package com.dir.music.user_information.controller.user_controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserControllerUpdateInput {
    private Long userId;
    private String userName;
    private String phoneNumber;
    private Date dateOfBirth;
}
