package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/19/2019
 * Time: 2:57 AM
 * Project: cloudHealthMainService
 */

public interface UserRepository extends JpaRepository<User, String> {
}
