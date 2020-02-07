package com.cloudHealth.desktopapp.uiControls;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 2/7/2020
 * Time: 9:53 AM
 * Project: desktop-app
 */

@Component
public class PractitionerController  implements Initializable {

    @FXML
    private ImageView profileImage;

    @FXML
    private JFXButton profilechooseBtn;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField fName;

    @FXML
    private JFXTextField mName;

    @FXML
    private JFXTextField sirName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField phoneNo;

    @FXML
    private JFXTextField nationality;

    @FXML
    private JFXTextField prctitionerId;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField skills;

    @FXML
    private JFXTextField healthUnitID;

    @FXML
    private JFXTextField healthUnitName;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXComboBox<?> gender;

    @FXML
    private JFXComboBox<?> role;

    @FXML
    private JFXTextArea roleSelected;

    @FXML
    private TextField practitionerSearch;

    @FXML
    private JFXButton uploadBtn;

    @FXML
    private JFXButton editBtn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
