package com.cloud.health.authorizationservice.service;

import com.cloud.health.authorizationservice.model.User;
import com.cloud.health.authorizationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword("{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
