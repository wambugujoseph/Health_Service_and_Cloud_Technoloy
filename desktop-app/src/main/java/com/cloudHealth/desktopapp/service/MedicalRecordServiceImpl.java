package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.MedicalRecord;
import com.cloudHealth.desktopapp.model.MedicalRecordList;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

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
    public Object getAllPatientMedicalRecords(String userIdOrEmail) {
        try {
            ResponseEntity<Object> medicalRecordResponseEntity;

            HttpEntity<String> requestBody = new HttpEntity<>(null,requestHttpHeaders.getHTTPRequestHeaders());
            medicalRecordResponseEntity = restTemplate
                    .exchange(PATIENT_RECORD_URL+userIdOrEmail, HttpMethod.GET,requestBody,Object.class);
            if (medicalRecordResponseEntity.getStatusCode().equals(HttpStatus.OK)){
                System.out.println(Objects.requireNonNull(medicalRecordResponseEntity.getBody()).toString());
                return medicalRecordResponseEntity.getBody();
            }else
                return null;
        } catch (Exception e) {
           if (e.getMessage().contains("500")){
               uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Could not find the record relating to that email or ID",
                       "ERROR", "500! InternalServer Error",null).show();
           }
            return null;
        }

    }
}
