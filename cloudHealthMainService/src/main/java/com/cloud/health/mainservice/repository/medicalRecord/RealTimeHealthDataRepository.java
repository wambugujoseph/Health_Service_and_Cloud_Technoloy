package com.cloud.health.mainservice.repository.medicalRecord;

import com.cloud.health.mainservice.model.entity.RealTimeDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/26/2019
 * Time: 1:07 PM
 * Project: cloudHealthMainService
 */

public interface RealTimeHealthDataRepository extends JpaRepository<RealTimeDataEntity,Integer> {

    List<RealTimeDataEntity> findAllByPatientId(int patientId);
}
