package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.HealthRecNotificationsEntity;
import com.cloud.health.mainservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 4:12 PM
 * Project: cloudHealthMainService
 */

public interface HealthRecNotificationRepository extends JpaRepository<HealthRecNotificationsEntity, Integer> {

    Optional<List<HealthRecNotificationsEntity>> findAllByUserByNotificationTo(UserEntity userEntity);
}
