package com.cloud.health.authorizationservice.service;

import com.cloud.health.authorizationservice.model.TempUser;
import com.cloud.health.authorizationservice.model.User;
import com.cloud.health.authorizationservice.repository.UserRepository;
import com.cloud.health.authorizationservice.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Project: CloudHealthAuthorizationService
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private User user;

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User saveUser(TempUser tempUser) {
        user.setPassword("{bcrypt}" + bCryptPasswordEncoder.encode(tempUser.getPassword()));
        user.setUsername(tempUser.getUsername());
        user.setEmail(tempUser.getEmail());
        user.setPhoneNumber(tempUser.getPhoneNumber());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRoles(null);
        return userRepository.save(user);
    }
}
