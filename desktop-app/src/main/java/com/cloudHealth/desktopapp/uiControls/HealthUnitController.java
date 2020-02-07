package com.cloudHealth.desktopapp.uiControls;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
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
    private JFXTextField healthUnitName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
