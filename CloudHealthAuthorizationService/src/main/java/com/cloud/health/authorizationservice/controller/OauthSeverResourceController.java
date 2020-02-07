package com.cloud.health.authorizationservice.controller;

import com.cloud.health.authorizationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Project: CloudHealthAuthorizationService
 */

@Controller
public class OauthSeverResourceController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

}
