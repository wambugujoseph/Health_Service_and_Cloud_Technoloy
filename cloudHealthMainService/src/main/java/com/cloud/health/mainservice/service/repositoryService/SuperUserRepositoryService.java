package com.cloud.health.mainservice.service.repositoryService;

import com.cloud.health.mainservice.model.HealthPractitioner;
import com.cloud.health.mainservice.model.HealthUnit;
import com.cloud.health.mainservice.model.entity.GeoLocationEntity;
import com.cloud.health.mainservice.model.entity.HealthUnitEntity;
import com.cloud.health.mainservice.model.entity.PractitionerEntity;
import com.cloud.health.mainservice.repository.GeoLocationRepository;
import com.cloud.health.mainservice.repository.HealthPractitionerRepository;
import com.cloud.health.mainservice.repository.HealthUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 12:12 AM
 * Project: cloudHealthMainService
 */

@Service
public class SuperUserRepositoryService {

    @Autowired
    private HealthUnitRepository healthUnitRepository;
    @Autowired
    private GeoLocationRepository geoLocationRepository;
    @Autowired
    private HealthPractitionerRepository practitionerRepository;
    @Autowired
    private PractitionerRepositoryService practitionerRepositoryService;

    /**
     * addHealthUnit Method
     * @param healthUnit
     * @return HealthUnitEntity object else it returns null
     */
    public HealthUnitEntity addHealthUnit(HealthUnit healthUnit){
        try {
            GeoLocationEntity geoLocationEntity = addGeoLocation(healthUnit.getGeoLocation());
            if (!(geoLocationEntity==null)) {
                healthUnit.setGeoLocation(getGeoLocation(geoLocationEntity.getLocationId()));
                return healthUnitRepository.save(getHeathUnitEntity(healthUnit));
            }
            return null;
        } catch (InvalidDataAccessApiUsageException e) {
            return null;
        }
    }

    /**
     * @param healthPractitioner
     * @return practitionerEntity
     */
    public PractitionerEntity addPractitioner(HealthPractitioner healthPractitioner){
        HealthUnitEntity healthUnitEntity =  practitionerRepositoryService.getHealthUnitEntity(healthPractitioner.getHealthOrgName());
        PractitionerEntity practitionerEntity = new PractitionerEntity();
        practitionerEntity.setPractitionerId(healthPractitioner.getPractitionerId());
        practitionerEntity.setTitle(healthPractitioner.getTitle());
        practitionerEntity.setSkill(healthPractitioner.getSkill());
        practitionerEntity.setDescription(healthPractitioner.getDescription());
        practitionerEntity.setUser(practitionerRepositoryService.getClientInfo(healthPractitioner.getUserId()));
        practitionerEntity.setHealthUnit(healthUnitEntity);
        practitionerEntity.setHealthUnitId(healthUnitEntity.getHealthUnitId());
        return practitionerRepository.save(practitionerEntity);
    }

    /**
     * @param iD unique identifier of GeoLocation stored in the database
     * @return geoLocation object
     */
    public GeoLocationEntity getGeoLocation(int iD){
        return geoLocationRepository.findById(iD).get();
    }

    /**
     * addGeoLocation method
     * @param geoLocationEntity it is the object to be stored in the database
     * @return geoLocationEntity object if the transaction is performed successfully
     */
    public GeoLocationEntity addGeoLocation(GeoLocationEntity geoLocationEntity){
        return geoLocationRepository.save(geoLocationEntity);
    }


    public HealthUnitEntity getHeathUnitEntity(HealthUnit healthUnit){
        HealthUnitEntity healthUnitEntity = new HealthUnitEntity();
        healthUnitEntity.setDescription(healthUnit.getDescription());
        healthUnitEntity.setGeoLocation(healthUnit.getGeoLocation());
        healthUnitEntity.setHealthUnitId(healthUnit.getHealthUnitId());
        healthUnitEntity.setHealthUnitName(healthUnit.getHealthUnitName());
        return healthUnitEntity;
    }
}
