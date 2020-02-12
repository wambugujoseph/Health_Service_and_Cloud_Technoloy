package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.GeoLocation;
import com.cloudHealth.desktopapp.model.HealthUnit;
import com.cloudHealth.desktopapp.service.HealthUnitService;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 2/7/2020
 * Time: 9:54 AM
 * Project: desktop-app
 */
@Component
public class HealthUnitController implements Initializable {

    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField longitude;
    @FXML
    private JFXTextField latitude;
    @FXML
    private JFXButton uploadBtn;
    @FXML
    private TextField searchHealthUnit;
    @FXML
    private JFXButton editBtn;
    @FXML
    private JFXTextArea locationDescription;
    @FXML
    private JFXTextField healthUnitName;

    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;
    @Autowired
    private HealthUnitService healthUnitService;

    private int locationId=0;
    private int healthUnitId  = 0;

    private Logger logger = LoggerFactory.getLogger(HealthUnitController.class);



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editBtn.setDisable(true);

    }

    @FXML
    public void setUploadBtnActionEvent(){

        if (!(description.getText().isEmpty() && longitude.getText().isEmpty() && latitude.getText().isEmpty() && healthUnitName.getText().isEmpty())) {
            try {
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.setError(new BigDecimal(0.0));
                geoLocation.setLatitude(BigDecimal.valueOf(Double.parseDouble(latitude.getText())));
                geoLocation.setLongitude(BigDecimal.valueOf(Double.parseDouble(longitude.getText())));
                geoLocation.setLocationDescription(locationDescription.getText());
                geoLocation.setLocationId(locationId);

                HealthUnit healthUnit = new HealthUnit();
                healthUnit.setGeoLocation(geoLocation);
                healthUnit.setDescription(description.getText());
                healthUnit.setHealthUnitId(healthUnitId);
                healthUnit.setHealthUnitName(healthUnitName.getText());

                healthUnitService.uploadHealthUnitDetails(healthUnit);
            } catch (Exception e) {
                //e.printStackTrace();
                uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,e.getMessage(),"Error", "Incorrect Information Provided",null).show();
            }
        }

    }

    @FXML
    public void enableEdit(){
        description.setDisable(false);
        longitude.setDisable(false);
        latitude.setDisable(false);
        editBtn.setDisable(false);
        locationDescription.setDisable(false);
        healthUnitName.setDisable(false);
    }

    @FXML
    public void setSearchHealthUnit(KeyEvent event){
        if (event.getCode().equals(KeyCode.ENTER)){
            editBtn.setDisable(false);
            if (!(searchHealthUnit.getText().isEmpty())){
                logger.info("Enter key event received ++");
                try {
                    HealthUnit healthUnit = healthUnitService.searchHealthUnit(searchHealthUnit.getText());
                    this.healthUnitId = healthUnit.getHealthUnitId();
                    this.locationId = healthUnit.getGeoLocation().getLocationId();
                    healthUnitName.setText(healthUnit.getHealthUnitName());
                    longitude.setText(""+healthUnit.getGeoLocation().getLongitude());
                    latitude.setText(""+healthUnit.getGeoLocation().getLatitude());
                    locationDescription.setText(healthUnit.getGeoLocation().getLocationDescription());
                    description.setText(healthUnit.getDescription());
                } catch (Exception e) {
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION,"Colud not find Health Unit with that Id", "Information",null, null).show();
                }

            }

        }

    }

}
