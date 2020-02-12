package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.HealthUnit;
import com.cloudHealth.desktopapp.model.Practitioner;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.cloudHealth.desktopapp.config.Constant.HEALTH_PRACTITIONER;
import static com.cloudHealth.desktopapp.config.Constant.HEALTH_UNIT;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 2/9/2020
 * Time: 10:21 PM
 * Project: desktop-app
 */

@Service
public class HealthUnitService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RequestHttpHeaders requestHttpHeaders;
    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;

    private Logger logger  = LoggerFactory.getLogger(HealthUnitService.class);

    public void uploadHealthUnitDetails(HealthUnit healthUnit){

        try {
            ResponseEntity<HealthUnit> response;
            HttpHeaders headers = requestHttpHeaders.getHTTPRequestHeaders();
            HttpEntity<HealthUnit> requestBody = new HttpEntity<>(healthUnit,headers);
            response = restTemplate
                    .exchange(HEALTH_UNIT, HttpMethod.POST, requestBody, HealthUnit.class);

            if(response.getStatusCode().equals(HttpStatus.CREATED)){
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION, "Heath Unit Registered Successfully","Confirmation",null,null).show();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Faile to reguster Health Unit", "Error", null, null).show();
        } catch (RestClientException e) {
            e.printStackTrace();
            uiAlertsAndPopUp.showAlert(
                    Alert.AlertType.ERROR, e.getMessage(),"Error", null, null
            ).show();
        }
    }

    public HealthUnit searchHealthUnit(String healthUnitId){
        try {
            ResponseEntity<HealthUnit> response;
            HttpHeaders headers = requestHttpHeaders.getHTTPRequestHeaders();
            HttpEntity<String> requestBody = new HttpEntity<>(headers);
            response = restTemplate
                    .exchange(HEALTH_UNIT+"/"+healthUnitId, HttpMethod.GET, requestBody, HealthUnit.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                return response.getBody();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Failed to Get Health Unit", "Error", null, null).show();
            return new HealthUnit();
        } catch (RestClientException e) {
            e.printStackTrace();
            uiAlertsAndPopUp.showAlert(
                    Alert.AlertType.ERROR, e.getMessage(),"Error", null, null
            ).show();
            return new HealthUnit();
        }
    }
}
