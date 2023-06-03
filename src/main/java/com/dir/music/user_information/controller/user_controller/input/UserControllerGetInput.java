package com.dir.music.user_information.controller.user_controller.input;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserControllerGetInput {
    private Long userId;
    private boolean isDetailed;
}
