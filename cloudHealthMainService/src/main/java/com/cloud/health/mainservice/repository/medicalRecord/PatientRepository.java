package com.cloud.health.mainservice.repository.medicalRecord;

import com.cloud.health.mainservice.model.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 12/22/2019
 * Time: 1:49 AM
 * Project: cloudHealthMainService
 */

public interface PatientRepository extends JpaRepository<PatientEntity,Integer> {
    Optional<PatientEntity>findByUser(String userId);
}
