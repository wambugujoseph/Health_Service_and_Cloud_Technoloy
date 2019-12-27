package com.cloud.health.authorizationservice.controller;

import com.cloud.health.authorizationservice.model.TempUser;
import com.cloud.health.authorizationservice.model.User;
import com.cloud.health.authorizationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthRestController {

    @Autowired
    public UserServiceImpl userServiceImpl;

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody TempUser tempUser) {
        try {
            return userServiceImpl.saveUser(tempUser);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return new User();
        }
    }
}