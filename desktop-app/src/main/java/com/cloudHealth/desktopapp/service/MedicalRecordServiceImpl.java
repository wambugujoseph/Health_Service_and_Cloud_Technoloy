package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.MedicalRecord;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;

import static com.cloudHealth.desktopapp.config.Constant.PATIENT_RECORD_URL;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 1/27/2020
 * Time: 12:44 AM
 * Project: desktop-app
 */

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RequestHttpHeaders requestHttpHeaders;
    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;

    @Override
    public MedicalRecord[] getAllPatientMedicalRecords(String userIdOrEmail) {
        try {
            ResponseEntity<MedicalRecord[]> medicalRecordResponseEntity;

            HttpEntity<String> requestBody = new HttpEntity<>(null,requestHttpHeaders.getHTTPRequestHeaders());
            medicalRecordResponseEntity = restTemplate
                    .exchange(PATIENT_RECORD_URL+userIdOrEmail, HttpMethod.GET,requestBody, MedicalRecord[].class);
            if (medicalRecordResponseEntity.getStatusCode().equals(HttpStatus.OK)){
                return medicalRecordResponseEntity.getBody();
            }
                return null;
        } catch (Exception e) {
           if (e.getMessage().contains("500")){
               uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Could not find the record relating to that email or ID",
                       "ERROR", "500! InternalServer Error",null).show();
           }
           e.printStackTrace();
            return null;
        }

    }
}
