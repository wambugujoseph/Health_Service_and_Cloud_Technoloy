package com.cloud.health.mainservice.service;

import com.cloud.health.mainservice.model.entity.HealthRecNotificationsEntity;
import com.cloud.health.mainservice.model.entity.UserEntity;
import com.cloud.health.mainservice.repository.HealthRecNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cloud.health.mainservice.util.Constant.API_V_1;
import static com.cloud.health.mainservice.util.Constant.BASE_URL;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/26/2019
 * Time: 11:52 AM
 * Project: cloudHealthMainService
 */
@Service
public class Notifications {

    @Autowired
    private HealthRecNotificationRepository healthRecNotificationRepository;

    public boolean creatPersonalDrAcceptanceNotification(UserEntity practitioner, UserEntity client){
        HealthRecNotificationsEntity healthRecNotificationsEntity = new HealthRecNotificationsEntity();
        String message = practitioner.getFirstName() + " accepted to your personal health practitioner";
        return createNotification(practitioner, client, healthRecNotificationsEntity, message, 1, healthRecNotificationRepository);
    }

    public boolean createAccessContractAcceptanceNotification(UserEntity grantedTo, UserEntity granter) {
        HealthRecNotificationsEntity healthRecNotificationsEntity = new HealthRecNotificationsEntity();
        String message = grantedTo.getFirstName() + " accepted your Medical record access contract";

        return createNotification(grantedTo, granter, healthRecNotificationsEntity, message, 1, healthRecNotificationRepository);
    }

    /**
     * createAccessContractRequestNotification method
     * @param grantedTo the person to whom access to medical is granted
     * @param granter person granting access to his/her medical record
     * @param token string of char
     * @param contractId unique identifier of access cntract
     * @return true if process is executed successfully
     */
    public boolean createAccessContractRequestNotification(UserEntity grantedTo, UserEntity granter, String token, int contractId) {
        HealthRecNotificationsEntity healthRecNotificationsEntity = new HealthRecNotificationsEntity();
        String message = "Hi " + grantedTo.getFirstName() + "\n" +
                granter.getFirstName() + " " + granter.getMiddleName() + " would like you to be accessing and sharing " + getGender(granter.getGender()) +
                " medical record in case of an emergency";

        healthRecNotificationsEntity.setNotificationFrom(grantedTo.getUserId());
        healthRecNotificationsEntity.setMessage(message);
        healthRecNotificationsEntity.setAccepted(0);
        healthRecNotificationsEntity.setAcceptUrl(BASE_URL + "/" + API_V_1 + "/client/accept/access-contract/" + token + "/" + contractId);
        healthRecNotificationsEntity.setNotificationFrom(granter.getUserId());
        healthRecNotificationsEntity.setUserByNotificationFrom(granter);
        healthRecNotificationsEntity.setUserByNotificationTo(grantedTo);
        healthRecNotificationRepository.save(healthRecNotificationsEntity);
        return true;
    }

    /**
     * createNotification defauth notification creator method
     * @param practitioner object
     * @param client object
     * @param healthRecNotificationsEntity object
     * @param message Message to be sent
     * @param i
     * @param healthRecNotificationRepository  persistence object
     * @return true if process succeeds else false
     */
    public  boolean createNotification(UserEntity practitioner, UserEntity client, HealthRecNotificationsEntity healthRecNotificationsEntity,
                                             String message, int i, HealthRecNotificationRepository healthRecNotificationRepository) {
        healthRecNotificationsEntity.setNotificationFrom(practitioner.getUserId());
        healthRecNotificationsEntity.setMessage(message);
        healthRecNotificationsEntity.setAcceptUrl(null);
        healthRecNotificationsEntity.setAccepted(i);
        healthRecNotificationsEntity.setUserByNotificationFrom(practitioner);
        healthRecNotificationsEntity.setUserByNotificationTo(client);
        healthRecNotificationRepository.save(healthRecNotificationsEntity);
        return true;
    }

    /**
     * the method creates notification
     * @param client object
     * @param practitioner object
     * @param token  string
     * @param practitionerId unique identifier of practitioner
     * @param clientId unique identifier of client
     * @return true or false
     */
    public boolean createPersonalDrRequestNotification(UserEntity client, UserEntity practitioner, String token, String practitionerId, String clientId){

        String message = "Hi "+practitioner.getFirstName()+" \n"+
                client.getFirstName()+" "+client.getMiddleName()+" said that you are "+getGender(client.getGender())+" personal medical practitioner";

        HealthRecNotificationsEntity healthRecNotificationsEntity = new HealthRecNotificationsEntity();
        healthRecNotificationsEntity.setMessage(message);
        healthRecNotificationsEntity.setAccepted(0);
        healthRecNotificationsEntity.setNotificationFrom(client.getUserId());
        healthRecNotificationsEntity.setNotificationTo(practitioner.getUserId());
        healthRecNotificationsEntity.setUserByNotificationTo(practitioner);
        healthRecNotificationsEntity.setUserByNotificationFrom(client);
        healthRecNotificationsEntity.setAcceptUrl(BASE_URL+"/"+API_V_1+"/practitioner/accept/request/"+token+"/"+practitionerId+"/"+clientId);
        try {
            healthRecNotificationRepository.save(healthRecNotificationsEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get gender method
     * @param objGender  which is either male or female
     * @return his or her
     */
    private String getGender(String objGender) {
        String gender;
        if (objGender.equalsIgnoreCase("male"))
            gender="his";
        else
            gender="her";
        return gender;
    }
}
