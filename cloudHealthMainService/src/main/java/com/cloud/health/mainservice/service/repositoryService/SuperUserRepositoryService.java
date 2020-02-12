package com.cloud.health.mainservice.service.repositoryService;

import com.cloud.health.mainservice.model.HealthPractitioner;
import com.cloud.health.mainservice.model.HealthUnit;
import com.cloud.health.mainservice.model.User;
import com.cloud.health.mainservice.model.entity.GeoLocationEntity;
import com.cloud.health.mainservice.model.entity.HealthUnitEntity;
import com.cloud.health.mainservice.model.entity.PractitionerEntity;
import com.cloud.health.mainservice.model.entity.UserEntity;
import com.cloud.health.mainservice.repository.ClientRepository;
import com.cloud.health.mainservice.repository.GeoLocationRepository;
import com.cloud.health.mainservice.repository.HealthPractitionerRepository;
import com.cloud.health.mainservice.repository.HealthUnitRepository;
import com.cloud.health.mainservice.service.filestorage.ProfileFileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private HealthPractitionerRepository healthPractitionerRepository;
    @Autowired
    private ProfileFileStorageService profileFileStorageService;
    @Autowired
    private ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(SuperUserRepositoryService.class);

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

    public HealthUnitEntity geTHealthUnit(String healthUnitId){
        try {
            int id = Integer.parseInt(healthUnitId);
            return healthUnitRepository.findById(id).orElse(new HealthUnitEntity());
        } catch (NumberFormatException e) {
            logger.error("Failed to get Health unit using the provided id");
            e.printStackTrace();
            return new HealthUnitEntity();
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

    public ResponseEntity<Object> addPractitioner(String practitioner, String user,MultipartFile file) throws ParseException {
        try {
            //save Practitioner Profile
            String fileName = profileFileStorageService.store(file);
            //save user
            PractitionerEntity practitionerEntity;
            //logger.info("user "+user);
            //logger.info("practitioner "+practitioner);
            User castedUser = objectMapper.readValue(user, User.class);
            UserEntity userEntity = practitionerRepositoryService.getCompleteUserObject(castedUser,fileName);
            practitionerEntity = objectMapper.readValue(practitioner, PractitionerEntity.class);
            UserEntity tempUserEntity =  clientRepository.save(userEntity);
            if(tempUserEntity.getEmail().equalsIgnoreCase(userEntity.getEmail())){
                practitionerEntity.setHealthUnit(
                        healthUnitRepository.findByHealthUnitId(practitionerEntity.getHealthUnitId()).orElse(new HealthUnitEntity()));
                practitionerEntity.setUser(tempUserEntity);
                PractitionerEntity practitionerEntity1 = healthPractitionerRepository.save(practitionerEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body(practitionerEntity1);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PractitionerEntity());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(""+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PractitionerEntity());
        }
    }

    public PractitionerEntity getPractitioner(String userIdOrPractitionerId){
       return healthPractitionerRepository.findByPractitionerIdOrUserId(userIdOrPractitionerId, userIdOrPractitionerId).orElse(new PractitionerEntity());
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
