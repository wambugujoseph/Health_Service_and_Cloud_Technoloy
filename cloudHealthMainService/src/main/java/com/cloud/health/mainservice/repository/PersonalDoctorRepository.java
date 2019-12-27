package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.PersonalDoctorEntity;
import com.cloud.health.mainservice.model.entity.PersonalDoctorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Tuesday
 * Date: 12/24/2019
 * Time: 10:30 AM
 * Project: cloudHealthMainService
 */

public interface PersonalDoctorRepository extends JpaRepository<PersonalDoctorEntity, PersonalDoctorEntityPK> {
    Optional<PersonalDoctorEntity> findByDoctorTo(String userId);
    Optional<PersonalDoctorEntity> findByPractitionerIdAndDoctorToAndToken(String practitionerId, String clientId,String token);
}
