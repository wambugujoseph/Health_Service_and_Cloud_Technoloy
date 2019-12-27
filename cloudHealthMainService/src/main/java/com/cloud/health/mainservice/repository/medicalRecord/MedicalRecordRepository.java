package com.cloud.health.mainservice.repository.medicalRecord;

import com.cloud.health.mainservice.model.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 11:28 PM
 * Project: cloudHealthMainService
 */

public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Integer> {

    Optional<MedicalRecordEntity> findByPatientId(int patientId);
}

