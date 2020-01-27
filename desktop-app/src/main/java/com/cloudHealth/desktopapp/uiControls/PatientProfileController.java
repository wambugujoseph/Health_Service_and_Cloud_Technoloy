package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.MedicalFile;
import com.cloudHealth.desktopapp.model.User;
import com.cloudHealth.desktopapp.model.UserProfile;
import com.cloudHealth.desktopapp.service.PatientProfileServiceImpl;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import com.cloudHealth.desktopapp.util.auth.JwtAuthUtil;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 1/18/2020
 * Time: 5:44 PM
 * Project: desktop-app
 */
@Component
public class PatientProfileController implements Initializable {

    @FXML private ImageView profilePhoto;
    @FXML private JFXTextField firstName;
    @FXML private JFXTextField clientID;
    @FXML private JFXTextField surName;
    @FXML private JFXTextField lastName;
    @FXML private JFXTextField email;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField nationality;
    @FXML private JFXDatePicker dob;
    @FXML private JFXTextArea medicalInsurance;
    @FXML private JFXTextArea healthComplication;
    @FXML private JFXTextArea specialNeeds;
    @FXML private JFXTextField bloodGroup;
    @FXML private JFXRadioButton genderMale;
    @FXML private JFXRadioButton genderFemale;

    @FXML private AnchorPane profileHeaderAndTab;
    @FXML private JFXButton newProfile;
    @FXML private TextField searchTxtField;
    @FXML private JFXButton editProfile;
    @FXML private JFXButton uploadProfile;

    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;
    @Autowired
    private PatientProfileServiceImpl patientProfileService;

    private final Resource profileHeaderAndTabPane;
    private final ApplicationContext ac;
    PatientProfileController(@Value("classpath:/templates/profileHeaderTabPane.fxml") Resource profileHeaderAndTabPane,
                             ApplicationContext ac){
        this.profileHeaderAndTabPane = profileHeaderAndTabPane;
        this.ac = ac;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        toggleGenderRadioButton();

    }

    @FXML
    public void setUploadProfile(){
        if (!clientID.getText().isEmpty() && !firstName.getText().isEmpty()&& !surName.getText().isEmpty() && !lastName.getText().isEmpty()&& ! email.getText().isEmpty() &&
        !phoneNumber.getText().isEmpty()&& !nationality.getText().isEmpty()){
            UserProfile userProfile;
            User user = new User(clientID.getText(),firstName.getText(), lastName.getText(), surName.getText(), email.getText(), phoneNumber.getText(),
                    dob.getValue().toString(), getGender(),nationality.getText());

            if (!bloodGroup.getText().isEmpty() || !specialNeeds.getText().isEmpty() || !healthComplication.getText().isEmpty() || !medicalInsurance.getText().isEmpty()){
                userProfile = new UserProfile(0,bloodGroup.getText(), specialNeeds.getText(), healthComplication.getText(), medicalInsurance.getText(), clientID.getText());
                boolean uploaded = patientProfileService.createProfile(user, userProfile);

                if(uploaded) {
                    clearProfileField();
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION, "Uploaded successfully", "Confimation", null, null).show();
                }else
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Error 500 Internal Server Error","Server Error",null, null).show();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Aleast one field on \"Medical Information\" should have data","Error",null, null).show();
        }else {
                uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"some of the fields are empty", "Error","Empty fields", null).show();
        }
    }
    private void toggleGenderRadioButton(){
        ToggleGroup genderRadioToggle = new ToggleGroup();
        genderFemale.setToggleGroup(genderRadioToggle);
        genderMale.setToggleGroup(genderRadioToggle);


    }

    private String getGender(){
        if (genderMale.isSelected())
            return "male";
        else if(genderFemale.isSelected())
            return "female";
        else return "";
    }

    private void clearProfileField(){
        clientID.clear();
        firstName.clear();
        surName.clear();
        lastName.clear();
        email.clear();
        phoneNumber.clear();
        nationality.clear();
        dob.setValue(LocalDate.now());
        medicalInsurance.clear();
        healthComplication.clear();
        specialNeeds.clear();
        bloodGroup.clear();
    }

    private void disableAllTextField(){
        profilePhoto.setDisable(true);
        firstName.setDisable(true);
        surName.setDisable(true);
        lastName.setDisable(true);
        email.setDisable(true);
        phoneNumber.setDisable(true);
        nationality.setDisable(true);
        dob.setDisable(true);
        medicalInsurance.setDisable(true);
        healthComplication.setDisable(true);
        specialNeeds.setDisable(true);
        bloodGroup.setDisable(true);
    }
}
