package com.cloud.health.mainservice.repository.medicalRecord;

import com.cloud.health.mainservice.model.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 8:46 AM
 * Project: cloudHealthMainService
 */

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Integer> {
}
