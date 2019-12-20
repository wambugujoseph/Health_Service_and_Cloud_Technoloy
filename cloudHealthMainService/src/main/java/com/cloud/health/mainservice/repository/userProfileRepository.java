package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/19/2019
 * Time: 11:46 PM
 * Project: cloudHealthMainService
 */
public interface userProfileRepository extends JpaRepository<UserProfile, Integer> {
}
