package com.cloud.health.authorizationservice.service;


import com.cloud.health.authorizationservice.model.TempUser;
import com.cloud.health.authorizationservice.model.User;

public interface UserService {

    User saveUser(User user);
    User saveUser(TempUser tempUser);

}
