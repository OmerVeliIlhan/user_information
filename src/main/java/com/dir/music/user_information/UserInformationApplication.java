package com.dir.music.user_information;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInformationApplication.class, args);
    }


}
