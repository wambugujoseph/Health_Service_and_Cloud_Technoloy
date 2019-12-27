package com.cloud.health.mainservice.repository.medicalRecord;

import com.cloud.health.mainservice.model.entity.MedicalFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 7:44 AM
 * Project: cloudHealthMainService
 */

public interface MedicalFileRepository extends JpaRepository<MedicalFileEntity, Integer> {
}
