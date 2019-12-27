package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.GeoLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 2:04 AM
 * Project: cloudHealthMainService
 */

public interface GeoLocationRepository extends JpaRepository<GeoLocationEntity,Integer> {
}
