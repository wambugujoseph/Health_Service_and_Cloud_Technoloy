package com.cloud.health.authorizationservice.repository;


import com.cloud.health.authorizationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Project: CloudHealthAuthorizationService
 */

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);

}


