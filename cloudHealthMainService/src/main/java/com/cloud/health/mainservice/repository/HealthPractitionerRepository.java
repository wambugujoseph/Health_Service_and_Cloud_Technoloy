package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.PractitionerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 6:02 AM
 * Project: cloudHealthMainService
 */

public interface HealthPractitionerRepository extends JpaRepository<PractitionerEntity,String> {

    Optional<PractitionerEntity> findByPractitionerIdOrUserId(String practitonerId, String userID);
}
