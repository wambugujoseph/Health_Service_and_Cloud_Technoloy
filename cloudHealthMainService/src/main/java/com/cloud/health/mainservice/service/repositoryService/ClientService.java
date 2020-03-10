package com.cloud.health.mainservice.service.repositoryService;

import com.cloud.health.mainservice.model.AccessContract;
import com.cloud.health.mainservice.model.CustomPrincipal;
import com.cloud.health.mainservice.model.PersonalHealthPractitioner;
import com.cloud.health.mainservice.model.RealTimeHealthData;
import com.cloud.health.mainservice.model.entity.*;
import com.cloud.health.mainservice.repository.*;
import com.cloud.health.mainservice.repository.medicalRecord.PatientRepository;
import com.cloud.health.mainservice.repository.medicalRecord.RealTimeHealthDataRepository;
import com.cloud.health.mainservice.service.Notifications;
import com.cloud.health.mainservice.util.Token;
import com.cloud.health.mainservice.util.auth.UserAuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 1:01 PM
 * Project: cloudHealthMainService
 */

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccessGrantsRepository accessGrantsRepository;
    @Autowired
    private HealthPractitionerRepository healthPractitionerRepository;
    @Autowired
    private PersonalDoctorRepository personalDoctorRepository;
    @Autowired
    private Notifications notifications;
    @Autowired
    private GeoLocationRepository geoLocationRepository;
    @Autowired
    private RealTimeDataEntity realTimeDataEntity;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RealTimeHealthDataRepository realTimeHealthDataRepository;
    @Autowired
    private UserAuthenticationDetails authenticationDetails;
    @Autowired
    protected HealthRecNotificationRepository healthRecNotificationRepository;

    public UserEntity getUserInfo(String userId) {
        return clientRepository.findByUserIdOrEmail(userId, userId).orElse(null);
    }

    public AccessContractEntity addAccessContract(AccessContract accessContract) {
        AccessContractEntity accessContractEntity = new AccessContractEntity();
        String token = new Token().generateRandomString(50);
        UserEntity grantedTo = getUserInfo(accessContract.getGrantedToUserIdOrEmail());
        UserEntity granter = getUserInfo(accessContract.getUserIdOrEmail());

        try {
            accessContractEntity.setAccessLevel(accessContract.getAccessLevel());
            accessContractEntity.setRelationship(accessContract.getRelationship());
            accessContractEntity.setUserByUserId(granter);
            accessContractEntity.setUserByGrantedTo(grantedTo);
            accessContractEntity.setGrantedTo(grantedTo.getUserId());
            accessContractEntity.setUserId(granter.getUserId());
            accessContractEntity.setToken(token);
            accessContractEntity = accessGrantsRepository.save(accessContractEntity);
            notifications.createAccessContractRequestNotification(grantedTo, granter, token, accessContractEntity.getContractId());
            return accessContractEntity;
        } catch (Exception e) {
           // e.printStackTrace();
            return  null;
        }
    }

    public List<AccessContractEntity> getAccessContractEntities(){
            String email = authenticationDetails.getUserEmail().getEmail();
            UserEntity userEntity = getUserInfo(email);
            return accessGrantsRepository.findByUserByUserId(userEntity);
    }

    public boolean deleteAccessGrant(String grantedToEmail){

        String userId = getUserInfo(authenticationDetails.getUserEmail().getEmail()).getUserId();

        AccessContractEntity accessContractEntity =
                accessGrantsRepository.findByUserByUserIdAndUserByGrantedTo(getUserInfo(userId), getUserInfo(grantedToEmail));
        try {
            accessGrantsRepository.deleteById(accessContractEntity.getContractId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public   AccessContractEntity getAccessContractEntity(int id){
        return accessGrantsRepository.findById(id).orElse(new AccessContractEntity());
    }

    public AccessContractEntity blockAccessGrant(String grantedToEmail, int state){

        String grantedToId = getUserInfo(grantedToEmail).getUserId();
        String userId = getUserInfo(authenticationDetails.getUserEmail().getEmail()).getUserId();
        AccessContractEntity accessContractEntity = accessGrantsRepository.findByUserIdAndGrantedTo(userId,grantedToId);
        accessContractEntity.setActive(state);
        return accessGrantsRepository.save(accessContractEntity);
    }

    public AccessContractEntity acceptMedicalRecordAccessContract(String token, int accessContractId) {
        if (accessGrantsRepository.findByTokenIsContaining(token).getToken().equalsIgnoreCase(token)) {
            try {
                AccessContractEntity accessContractEntity = accessGrantsRepository.findById(accessContractId).orElse(null);
                AccessContractEntity accessContract = accessGrantsRepository.findById(accessContractId).orElse(null);
                assert accessContractEntity != null;
                accessContractEntity.setActive(1);
                accessContractEntity.setToken(null);
                AccessContractEntity accessContractEntity1 = accessGrantsRepository.save(accessContractEntity);
                assert accessContract != null;
                notifications.createAccessContractAcceptanceNotification(accessContract.getUserByGrantedTo(), accessContract.getUserByUserId());
                return accessContractEntity1;
            } catch (Exception e) {
                return null;
            }

        } else
            return null;
    }

    public PersonalDoctorEntity addPersonalHealthPractitioner(PersonalHealthPractitioner personalHealthPractitioner) {
        if (!(personalHealthPractitioner.getClientIdOrEmail().isEmpty() && personalHealthPractitioner.getPractitionerUserIdOrEmail().isEmpty())) {
            UserEntity doctorUserInfo = clientRepository.findByUserIdOrEmail(
                    personalHealthPractitioner.getPractitionerUserIdOrEmail(), personalHealthPractitioner.getPractitionerUserIdOrEmail()).orElse(null);

            assert doctorUserInfo != null;
            PractitionerEntity practitioner = healthPractitionerRepository.findByPractitionerIdOrUserId(
                    doctorUserInfo.getUserId(), doctorUserInfo.getUserId())
                    .orElse(null);

            PersonalDoctorEntity personalDoctorEntity = new PersonalDoctorEntity();
            String token = new Token().generateRandomString(50);
            UserEntity client = clientRepository.findByUserIdOrEmail(
                   personalHealthPractitioner.getClientIdOrEmail(), personalHealthPractitioner.getClientIdOrEmail()).orElse(null);

            personalDoctorEntity.setActive(0);
            personalDoctorEntity.setToken(token);
            assert practitioner != null;
            personalDoctorEntity.setPractitionerId(practitioner.getPractitionerId());
            assert client != null;
            personalDoctorEntity.setDoctorTo(client.getUserId());
            //Creating notification message for the personal health practitioner
            notifications.createPersonalDrRequestNotification(client, practitioner.getUser(),
                    token, practitioner.getPractitionerId(), client.getUserId());

            return personalDoctorRepository.save(personalDoctorEntity);
        }
        return null;
    }

    public RealTimeDataEntity addRealTimeHealthData(RealTimeHealthData realTimeHealthData){

        GeoLocationEntity geoLocationEntity = realTimeHealthData.getGeoLocation();
        try {
            geoLocationEntity = geoLocationRepository.save(geoLocationEntity);
            if (geoLocationEntity != null){
                UserEntity userEntity = clientRepository.findByUserIdOrEmail(realTimeHealthData.getPatientUserIdOrEmail(),realTimeHealthData.getPatientUserIdOrEmail()).orElse(null);
                PatientEntity patientEntity = patientRepository.findByUser(Objects.requireNonNull(userEntity).getUserId()).orElse(null);

                try {
                    realTimeDataEntity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
                    realTimeDataEntity.setGeoLocationByLocationId(geoLocationEntity);
                    realTimeDataEntity.setLocationId(geoLocationEntity.getLocationId());
                    realTimeDataEntity.setRecordType(realTimeHealthData.getRecordType());
                    realTimeDataEntity.setValue(realTimeHealthData.getValue());
                    realTimeDataEntity.setPatientByPatientId(patientEntity);
                    realTimeDataEntity.setPatientId(Objects.requireNonNull(patientEntity).getPatientId());
                    return realTimeHealthDataRepository.save(realTimeDataEntity);

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<HealthRecNotificationsEntity> getAllNotifications(){
        String email = authenticationDetails.getUserEmail().getEmail();

        UserEntity userEntity= getUserInfo(email);
        return healthRecNotificationRepository.findAllByUserByNotificationTo(userEntity).orElse(new ArrayList<>());
    }


}
