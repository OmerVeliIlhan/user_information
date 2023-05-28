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
public class AuthServiceGetClaimsOutput implements IServiceOutput {
    private long subject;
    private String username;
    private String role;
    private String audience;
    private String issuer;
    private long expiration;
    private long issuedAt;
}
