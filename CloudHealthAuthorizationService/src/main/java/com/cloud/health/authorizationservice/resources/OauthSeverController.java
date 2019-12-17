package com.cloud.health.authorizationservice.resources;

import com.cloud.health.authorizationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OauthSeverController {

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

//    @PostMapping("/register")
//    public User registerUser(@ModelAttribute User user){
//
//        user.setAccountNonExpired(true);
//        user.setAccountNonLocked(true);
//        user.setCredentialsNonExpired(true);
//        user.setEnabled(true);
//
//        try {
//            return userServiceImpl.saveUser(user);
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//            return new User();
//        }
//    }
}
