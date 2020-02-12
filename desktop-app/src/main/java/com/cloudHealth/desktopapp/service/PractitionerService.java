package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.MedicalFile;
import com.cloudHealth.desktopapp.model.Practitioner;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import javafx.scene.control.Alert;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

import static com.cloudHealth.desktopapp.config.Constant.HEALTH_PRACTITIONER;
import static com.cloudHealth.desktopapp.config.Constant.PRACTITIONER_DETAILS;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 2/8/2020
 * Time: 11:39 AM
 * Project: desktop-app
 */
@Service
public class PractitionerService {

    @Autowired
    private RequestHttpHeaders requestHttpHeaders;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;

    private Logger logger = LoggerFactory.getLogger(PractitionerService.class);

    public Practitioner uploadPractitionerRegistration(JSONObject JsonPractitionerDetails, JSONObject JsonPractitionerUserDetails, File profile){

        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            FileSystemResource file = new FileSystemResource(profile);
            body.add("user", JsonPractitionerUserDetails.toJSONString());
            body.add("file", file);
            body.add("practitioner", JsonPractitionerDetails.toJSONString());
            ResponseEntity<Practitioner> response;
            HttpHeaders headers = requestHttpHeaders.getHTTPRequestHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(body,headers);
            response = restTemplate
                    .exchange(HEALTH_PRACTITIONER, HttpMethod.POST, requestBody, Practitioner.class);

            if (response.getStatusCode().equals(HttpStatus.CREATED)){
                return response.getBody();
            }
            return new Practitioner();
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("Failed to process practitioner register : "+ e.getMessage());
            return new Practitioner();
        }
    }

    public Practitioner getPractitionerFromCloud(String userIdOrEmail){
        try {
            ResponseEntity<Practitioner> response;
            HttpHeaders headers = requestHttpHeaders.getHTTPRequestHeaders();
            HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(headers);
            response = restTemplate
                    .exchange(PRACTITIONER_DETAILS+"/"+userIdOrEmail, HttpMethod.GET, requestBody, Practitioner.class);
            if (response.getStatusCode().equals(HttpStatus.OK)){
                return response.getBody();
            }
        } catch (RestClientException e) {
            logger.error("Could Not find Health practitioner, coused by :"+ e.getMessage());
            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR, "Could Not find Health practitioner, caused by :"+ e.getMessage(),"Error",null, null).show();
        }
        return new Practitioner();
    }
}
