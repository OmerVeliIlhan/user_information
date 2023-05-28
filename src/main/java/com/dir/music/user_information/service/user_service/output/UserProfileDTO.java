package com.dir.music.user_information.service.user_service.output;

import com.dir.music.user_information.service.IServiceOutput;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO implements IServiceOutput {
    private String userName;
    private Integer followerCount;
    private boolean includePrivateInformation;
    private Date dateOfBirth;
    private String phoneNumber;
}
