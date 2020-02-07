package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.*;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

import static com.cloudHealth.desktopapp.config.Constant.*;

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

    Logger logger = LoggerFactory.getLogger(MedicalRecordServiceImpl.class);

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

    @Override
    public Practitioner getPractitioner(String userIdOrEmail) {
        try {
            ResponseEntity<Practitioner> response;

            HttpEntity<String> requestBody = new HttpEntity<>(requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate
                    .exchange(PRACTITIONER_DETAILS+userIdOrEmail, HttpMethod.GET, requestBody, Practitioner.class);
            if (response.getStatusCode().equals(HttpStatus.OK)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to get Health Practitioner == "+ e.getMessage());
        }
       return new Practitioner();
    }

    @Override
    public MedicalRecord createMedicalRecord(HealthRecord healthRecord) {
        try {
            ResponseEntity<MedicalRecord> response;
            HttpEntity<HealthRecord> requestBody = new HttpEntity<>(healthRecord,requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate
                    .exchange(PATIENT_RECORD_URL, HttpMethod.POST, requestBody, MedicalRecord.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to Create Health medical record == "+ e.getMessage());
        }
        return new MedicalRecord();
    }

    @Override
    public Consultation uploadConsultation(Consultation consultation) {
        try {
            ResponseEntity<Consultation> response;
            HttpEntity<Consultation> requestBody = new HttpEntity<>(consultation,requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate
                    .exchange(MEDICAL_CONSULTATION, HttpMethod.POST, requestBody, Consultation.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to uploade medical consultation == "+ e.getMessage());
        }
        return new Consultation();
    }

    @Override
    public Surgery uploadSurgery(Surgery surgery) {
        try {
            ResponseEntity<Surgery> response;
            HttpEntity<Surgery> requestBody = new HttpEntity<>(surgery,requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate
                    .exchange(MEDICAL_SURGERY, HttpMethod.POST, requestBody, Surgery.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to upload Medical surgery == "+ e.getMessage());
        }
        return new Surgery();
    }

    @Override
    public Ailment uploadAilment(Ailment ailment) {
        try {
            ResponseEntity<Ailment> response;
            HttpEntity<Ailment> requestBody = new HttpEntity<>(ailment,requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate
                    .exchange(MEDICAL_AILMENT, HttpMethod.POST, requestBody, Ailment.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to Upload Medical ailment == "+ e.getMessage());
        }
        return new Ailment();
    }

    @Override
    public Prescription uploadPrescriptionAndMedication(Prescription prescription) {
        try {
            ResponseEntity<Prescription> response;
            HttpEntity<Prescription> requestBody = new HttpEntity<>(prescription,requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate
                    .exchange(MEDICAL_PRESCRIPTION, HttpMethod.POST, requestBody, Prescription.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to Upload Medical Prescription == "+ e.getMessage());
        }
        return new Prescription();
    }

    @Override
    public MedicalFile uploadMedicalFile(MultiValueMap<String, Object> body) {
        try {
            ResponseEntity<MedicalFile> response;
            HttpHeaders headers = requestHttpHeaders.getHTTPRequestHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(body,headers);
            response = restTemplate
                    .exchange(MEDICAL_FILE, HttpMethod.POST, requestBody, MedicalFile.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to Upload Medical File == "+ e.getMessage());
        }
        return new MedicalFile();
    }

    @Override
    public RealTimeData[] getRealTimeRecord(String patientId) {
        try {
            ResponseEntity<RealTimeData[]> response;
            HttpEntity<String> requestBody = new HttpEntity<>(requestHttpHeaders.getHTTPRequestHeaders());
            response = restTemplate
                    .exchange(MEDICAL_REALTIME_DATE+patientId, HttpMethod.GET, requestBody, RealTimeData[].class);
            if (response.getStatusCode().equals(HttpStatus.OK)){
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Failed to Get Realtime patient info == "+ e.getMessage());
        }
        return null;
    }
}
