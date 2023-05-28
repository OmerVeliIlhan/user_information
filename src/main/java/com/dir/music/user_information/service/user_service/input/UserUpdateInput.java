package com.dir.music.user_information.service.user_service.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateInput {
    private String userName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String token;
}
