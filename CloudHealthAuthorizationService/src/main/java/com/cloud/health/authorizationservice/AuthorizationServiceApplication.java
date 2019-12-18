package com.cloud.health.authorizationservice;

import com.cloud.health.authorizationservice.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthorizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPwdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public User getUser(){
        return new User();
    }

}
