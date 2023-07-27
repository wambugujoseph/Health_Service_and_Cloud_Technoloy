package com.cloudHealth.desktopapp.uiControls.uiHelper;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Tuesday
 * Date: 1/28/2020
 * Time: 5:28 PM
 * Project: desktop-app
 */

@Component
public class ConsultationDisplayController implements Initializable {
    @FXML
    private Text typeDisplay;
    @FXML
    private Text descriptionDisplay;
    @FXML
    private JFXButton delBtn;
    @FXML
    private JFXButton editBtn;
    @FXML
    private Label dateLabel;
    @FXML
    private Label patientId;
    @FXML
    private Label recordId;
    @FXML
    private Label consultationId;

    Logger logger = LoggerFactory.getLogger(ConsultationDisplayController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void editConsultation(){
        logger.info("test working :" + consultationId.getText());


    }
}
