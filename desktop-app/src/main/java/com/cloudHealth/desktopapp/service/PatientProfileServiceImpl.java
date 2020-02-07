package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.Patient;
import com.cloudHealth.desktopapp.model.Profile;
import com.cloudHealth.desktopapp.model.User;
import com.cloudHealth.desktopapp.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;;

import static com.cloudHealth.desktopapp.config.Constant.*;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/23/2020
 * Time: 10:16 PM
 * Project: desktop-app
 */

@Service
public class PatientProfileServiceImpl implements PatientProfileService  {
    @Autowired
    private RequestHttpHeaders requestHttpHeaders;
    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(PatientProfileServiceImpl.class);

    @Override
    public boolean createProfile(User user, Profile profile) {

        ResponseEntity<User> response;
        try {
            HttpEntity<User> requestBody = new HttpEntity<>(user, requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate.
                    exchange(CLIENT_REGISTER_URL, HttpMethod.POST, requestBody, User.class);
            if(response.getStatusCodeValue() < 203){
                HttpEntity<Profile> profileRequestBody = new HttpEntity<>(profile, requestHttpHeaders.getHTTPRequestHeaders());
                ResponseEntity<UserProfile> userProfileResponse = restTemplate.
                        exchange(CLIENT_PROFILE_URL, HttpMethod.POST, profileRequestBody, UserProfile.class);
                return userProfileResponse.getStatusCodeValue() < 203;
            }
                return true;
        } catch(Exception e) {
            logger.error("Failed to create the profile === "+ e.getMessage());

            return false;
        }
    }

    @Override
    public Patient getPatient(int patientId) {
        String tempPatientId = patientId+"";
        return findPatient(tempPatientId);
    }

    @Override
    public Patient getPatient(String userIdOrEmail) {

        return findPatient(userIdOrEmail);
    }

    @Override
    public UserProfile getUserProfile(String userIdOrEmail) {
        ResponseEntity<UserProfile> response;
        try {
            HttpEntity<String> requestBody = new HttpEntity<>(requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate.
                    exchange(CLIENT_PROFILE_URL+"/"+userIdOrEmail, HttpMethod.GET, requestBody, UserProfile.class);

            return response.getBody();
        } catch(Exception e) {
            logger.error("Failed to create the profile === "+ e.getMessage());

            return new UserProfile();
        }
    }

    private Patient findPatient(String tempPatientId) {
        try {
            ResponseEntity<Patient> responseEntity;
            HttpEntity<String> patientRequestBody = new HttpEntity<>("", requestHttpHeaders.getHTTPRequestHeaders());
            responseEntity = restTemplate.exchange(PATIENT_DETAILS + tempPatientId, HttpMethod.GET, patientRequestBody, Patient.class);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return responseEntity.getBody();
            }
            return new Patient();
        } catch (Exception e) {
            logger.error("Failed to get the patient: Caused by== "+e.getMessage());
        }
        return new Patient();
    }
}
