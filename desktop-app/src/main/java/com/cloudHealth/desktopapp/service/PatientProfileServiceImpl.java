package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.Patient;
import com.cloudHealth.desktopapp.model.User;
import com.cloudHealth.desktopapp.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

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

    @Override
    public boolean createProfile(User user, UserProfile userProfile) {

        ResponseEntity<User> response;
        try {
            HttpEntity<User> requestBody = new HttpEntity<>(user, requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate.
                    exchange(CLIENT_REGISTER_URL, HttpMethod.POST, requestBody, User.class);
            if(response.getStatusCodeValue() < 203){
                HttpEntity<UserProfile> profileRequestBody = new HttpEntity<>(userProfile, requestHttpHeaders.getHTTPRequestHeaders());
                ResponseEntity<UserProfile> userProfileResponse = restTemplate.
                        exchange(CLIENT_PROFILE_URL, HttpMethod.POST, profileRequestBody, UserProfile.class);
                return userProfileResponse.getStatusCodeValue() < 203;
            }
                return true;
        } catch(HttpClientErrorException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Patient getPatient(int patientId) {
        String tempPatientId = patientId+"";
        try {
            ResponseEntity<Patient> responseEntity;
            HttpEntity<String> patientRequestBody = new HttpEntity<>("",requestHttpHeaders.getHTTPRequestHeaders());
            responseEntity= restTemplate.exchange(PATIENT_DETAILS+tempPatientId,HttpMethod.GET,patientRequestBody,Patient.class);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)){
                return responseEntity.getBody();
            }
            return new Patient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Patient();
    }
}
