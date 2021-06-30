package com.jurassic.jurassiccrm.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class LoginConfiguration {

    @Bean
    public AuthenticationSuccessHandler getHandler() {
        return new LoginSuccessHandler();
    }

}
