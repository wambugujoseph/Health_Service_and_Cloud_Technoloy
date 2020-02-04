package com.cloud.health.mainservice.service.repositoryService;

import com.cloud.health.mainservice.model.User;
import com.cloud.health.mainservice.model.UserProfile;
import com.cloud.health.mainservice.model.entity.*;
import com.cloud.health.mainservice.repository.*;
import com.cloud.health.mainservice.repository.medicalRecord.PatientRepository;
import com.cloud.health.mainservice.service.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/19/2019
 * Time: 3:07 AM
 * Project: cloudHealthMainService
 */

@Service
public class PractitionerRepositoryService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientProfileRepository clientProfileRepository;
    @Autowired
    private HealthUnitRepository healthUnitRepository;
    @Autowired
    private PersonalDoctorRepository personalDoctorRepository;
    @Autowired
    private Notifications notifications;
    @Autowired
    private HealthPractitionerRepository healthPractitionerRepository;
    @Autowired
    private PatientRepository patientRepository;

    Logger logger = LoggerFactory.getLogger(PractitionerRepositoryService.class);


    /**
     * @param user to be persisted
     * @return true if user is added to the db else false
     */
    public boolean addClient(User user, String isUploadedPicName){
       UserEntity userEntity = clientRepository.save(getCompleteUserObject(user,isUploadedPicName));
       if(userEntity.equals(getCompleteUserObject(user,isUploadedPicName))){
           return true;
       }
       return false;
    }

    /**
     * Created user profile
     * @param userProfile to be saved
     * @return true if the user profile is created else return false
     */
    public UserProfileEntity addClientProfile(UserProfile userProfile){
        UserProfileEntity userProfileEntity =  getCompleteUserProfile(userProfile);
        try {
            clientProfileRepository.deleteByOwner(userProfileEntity.getOwner());
        } catch (Exception e) {
            logger.info("User does not exist : "+e.getMessage());
        }
        return clientProfileRepository.save(userProfileEntity);
    }

    public UserProfileEntity getClientProfile(String userEmailOrId){
        return clientProfileRepository.findByOwner(getClientInfo(userEmailOrId));
    }

    public UserEntity getUserInfo(String userId) {
        return clientRepository.findByUserIdOrEmail(userId, userId).orElse(null);
    }

    public UserEntity getClientInfo(String userId){
       Optional<UserEntity> user = clientRepository.findByUserIdOrEmail(userId,userId);
        return user.orElse(new UserEntity());
    }

    public HealthUnitEntity getHealthUnitEntity(String healthUnitName){
        Optional<HealthUnitEntity> optionalHealthUnit = healthUnitRepository.findByHealthUnitNameLike(healthUnitName);
        if (optionalHealthUnit.isPresent()){
            return optionalHealthUnit.get();
        }
        return new HealthUnitEntity();
    }

    public PractitionerEntity getPractitionerEntity(String userIdOrEmail){
        UserEntity user = getClientInfo(userIdOrEmail);
        return healthPractitionerRepository.findByPractitionerIdOrUserId(user.getUserId(), user.getUserId())
                .orElse(new PractitionerEntity());
    }

    public PersonalDoctorEntity acceptClientPersonalDoctor(String token, String practitionerId, String clientId){

        PersonalDoctorEntity personalDoctorEntity = null;
        try {
            personalDoctorEntity = personalDoctorRepository.findByPractitionerIdAndDoctorToAndToken(
                    practitionerId, clientId, token).orElse(null);
            assert personalDoctorEntity != null;
            personalDoctorEntity.setActive(1);
            personalDoctorEntity.setToken(null);
            notifications.creatPersonalDrAcceptanceNotification(
                    Objects.requireNonNull(healthPractitionerRepository.findById(practitionerId).orElse(null)).getUser(),
                    clientRepository.findById(clientId).orElse(null));
            return personalDoctorRepository.save(personalDoctorEntity);
        } catch (Exception e) {
           // e.printStackTrace();
            return null;
        }
    }

    private UserProfileEntity getCompleteUserProfile(UserProfile tempProfile){
        UserProfileEntity userProfile = new UserProfileEntity();
        userProfile.setBloodGroup(tempProfile.getBloodGroup());
        userProfile.setComplication(tempProfile.getComplication());
        userProfile.setInsuranceInformation(tempProfile.getInsuranceInformation());
        userProfile.setSpecialNeeds(tempProfile.getSpecialNeeds());
        userProfile.setOwner(getClientInfo(tempProfile.getOwnerId()));
        return userProfile;
    }

    private UserEntity getCompleteUserObject(User user, String profileUrl) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setMiddleName(user.getMiddleName());
        userEntity.setSirName(user.getSirName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setGender(user.getGender());
        userEntity.setNationality(user.getNationality());
        userEntity.setProfilePhotoUrl(profileUrl);
        userEntity.setUserId(user.getUserId());
        userEntity.setDob(getSqlDate(user.getDob()));
        return userEntity;
    }

    public PatientEntity getPatient(String patientID){
        int tempPatientID = 0;
        try {
            tempPatientID = Integer.parseInt(patientID);
            return  patientRepository.findByPatientIdOrUser(tempPatientID,patientID).orElse(new PatientEntity());
        } catch (Exception e) {
           logger.info(e.getMessage());
        }
        UserEntity userEntity = getClientInfo(patientID);
        return patientRepository.findByUser(patientID).orElse(new PatientEntity());
    }


    private Date getSqlDate(String date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyy");
            java.util.Date utilDate = simpleDateFormat.parse(date);
            return  new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
