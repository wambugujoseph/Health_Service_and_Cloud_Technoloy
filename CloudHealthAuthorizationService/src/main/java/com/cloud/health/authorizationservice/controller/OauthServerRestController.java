package com.cloud.health.authorizationservice.controller;

import com.cloud.health.authorizationservice.model.OauthClientDetails;
import com.cloud.health.authorizationservice.model.TempOauthClient;
import com.cloud.health.authorizationservice.model.TempUser;
import com.cloud.health.authorizationservice.model.User;
import com.cloud.health.authorizationservice.service.OauthClientDetailsServiceImpl;
import com.cloud.health.authorizationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cloud.health.authorizationservice.util.ApplicationConstants.API_V_1;

@RestController
@RequestMapping(API_V_1)
public class OauthServerRestController {

    @Autowired
    public UserServiceImpl userServiceImpl;
    @Autowired
    private OauthClientDetailsServiceImpl oauthClientDetailsServiceImpl;

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody TempUser tempUser) {
        try {
            return userServiceImpl.saveUser(tempUser);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return new User();
        }
    }

    @RequestMapping(value = "/application/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerApplication(@RequestBody OauthClientDetails oauthClientDetails){
        if (oauthClientDetails != null){
            TempOauthClient oauthClient = oauthClientDetailsServiceImpl.addOauthClientDetails(oauthClientDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(oauthClient);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}