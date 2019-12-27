package com.cloud.health.mainservice.repository.medicalRecord;

import com.cloud.health.mainservice.model.entity.ConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 12/22/2019
 * Time: 12:49 PM
 * Project: cloudHealthMainService
 */

public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Integer> {
}
