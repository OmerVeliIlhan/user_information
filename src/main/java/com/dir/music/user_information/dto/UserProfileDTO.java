package com.dir.music.user_information.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private String userName;
    private String userSurname;
    private Integer followerCount;

}
