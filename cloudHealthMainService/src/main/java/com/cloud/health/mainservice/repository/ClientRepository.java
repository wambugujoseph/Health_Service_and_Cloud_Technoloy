package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/19/2019
 * Time: 2:57 AM
 * Project: cloudHealthMainService
 */

public interface ClientRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserIdOrEmail(String userID, String email);
}
