package com.dir.music.user_information;

import feign.Client;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}
