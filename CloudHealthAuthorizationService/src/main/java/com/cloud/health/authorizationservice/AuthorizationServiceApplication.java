package com.cloud.health.authorizationservice;

import com.cloud.health.authorizationservice.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableEurekaClient
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
