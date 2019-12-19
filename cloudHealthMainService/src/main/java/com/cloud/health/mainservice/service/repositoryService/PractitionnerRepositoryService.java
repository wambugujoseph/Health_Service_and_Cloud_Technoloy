package com.cloud.health.mainservice.service.repositoryService;

import com.cloud.health.mainservice.model.entity.User;
import com.cloud.health.mainservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/19/2019
 * Time: 3:07 AM
 * Project: cloudHealthMainService
 */
public class PractitionnerRepositoryService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @param user to be persisted
     * @return true if user is added to the db else false
     */
    public boolean addClient(User user){
       User user1 = userRepository.save(user);
       if(user1.equals(user)){
           return true;
       }
       return false;
    }
}
