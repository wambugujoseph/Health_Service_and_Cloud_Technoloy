package com.cloud.health.authorizationservice.resources;

import com.cloud.health.authorizationservice.model.User;
import com.cloud.health.authorizationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthRestController {

    @Autowired
    public UserServiceImpl userServiceImpl;

    @PostMapping(value = "/register")
    public User registerUser(@RequestParam User user) {
        try {
            return userServiceImpl.saveUser(user);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return new User();
        }
    }
}
