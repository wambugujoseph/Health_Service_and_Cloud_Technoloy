package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.HealthUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 12:08 AM
 * Project: cloudHealthMainService
 */

public interface HealthUnitRepository extends JpaRepository<HealthUnitEntity, Integer> {
    Optional<HealthUnitEntity> findByHealthUnitNameLike(String healthUnitName);
    Optional<HealthUnitEntity> findByHealthUnitId(int id);

}
