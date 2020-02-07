package com.cloud.health.authorizationservice.controller;
import com.cloud.health.authorizationservice.model.TempOauthClient;
import com.cloud.health.authorizationservice.model.TempUser;
import com.cloud.health.authorizationservice.model.User;
import com.cloud.health.authorizationservice.service.OauthClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;



/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Project: CloudHealthAuthorizationService
 */

@RestController
public class OauthServerRestController {

    @Autowired
    public UserServiceImpl userServiceImpl;
    @Autowired
    private OauthClientDetailsServiceImpl oauthClientDetailsServiceImpl;

    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerUser(@RequestBody TempUser tempUser) {
        if (!tempUser.getEmail().equals("") && !tempUser.getPassword().equals("") && !tempUser.getPhoneNumber().equals("") &&
                !tempUser.getUsername().equals("") && !tempUser.getUserId().equals("")) {
            if (!tempUser.getEmail().isEmpty() && !tempUser.getPassword().isEmpty() && !tempUser.getPhoneNumber().isEmpty() &&
                    !tempUser.getUsername().isEmpty()) {

            try {
                User user = userServiceImpl.saveUser(tempUser);
                user.setPassword(null);
                return ResponseEntity.status(HttpStatus.CREATED).body(user);
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }else
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
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
