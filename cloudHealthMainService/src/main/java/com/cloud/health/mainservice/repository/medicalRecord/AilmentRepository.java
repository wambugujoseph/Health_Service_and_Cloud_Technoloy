package com.cloud.health.mainservice.repository.medicalRecord;

import com.cloud.health.mainservice.model.entity.AilmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 5:32 AM
 * Project: cloudHealthMainService
 */

public interface AilmentRepository extends JpaRepository<AilmentEntity, Integer> {
}
