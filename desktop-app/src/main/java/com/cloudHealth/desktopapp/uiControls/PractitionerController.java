package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.Practitioner;
import com.cloudHealth.desktopapp.service.PractitionerService;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import com.cloudHealth.desktopapp.util.Gender;
import com.cloudHealth.desktopapp.util.Role;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
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
    private JFXComboBox<Gender> gender;
    @FXML
    private JFXComboBox<Role> role;
    @FXML
    private JFXTextArea roleSelected;
    @FXML
    private TextField practitionerSearch;
    @FXML
    private JFXButton uploadBtn;
    @FXML
    private JFXButton editBtn;
    @FXML
    private AnchorPane profileImageHolder;

    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;
    @Autowired
    private PractitionerService practitionerService;

    Logger logger = LoggerFactory.getLogger(PractitionerController.class);

    private String profileUrl;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.gender.setItems(FXCollections.observableArrayList(Gender.values()));
        this.role.setItems(FXCollections.observableArrayList(Role.values()));
        this.roleSelected.setText("User");
        editBtn.setDisable(true);
    }

    @FXML
    public void getProfileImage() {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(profilechooseBtn.getScene().getWindow());
        URI url = file.toURI();
        ImageView imageView = new ImageView(url.toString());
        imageView.setFitHeight(profileImageHolder.getWidth());
        imageView.setFitWidth(profileImageHolder.getHeight());
        profileImageHolder.getChildren().add(imageView);
        profileUrl = file.getPath();
    }

    @FXML
    public void roleSelectedListener(){
        role.setOnAction(event -> {
          String selectedRole = role.getValue().toString();
          String roles = roleSelected.getText();
          if(!(roles.contains(selectedRole))){
              roleSelected.setText(roles+", "+selectedRole);
          }
        });
    }

    @FXML
    public void getAllEnteredInfo(){
        try {
            if (!(id.getText().isEmpty() && fName.getText().isEmpty() && sirName.getText().isEmpty() && mName.getText().isEmpty() && email.getText().isEmpty() &&
                    gender.getValue().toString().isEmpty() && nationality.getText().isEmpty() && prctitionerId.getText().isEmpty() && title.getText().isEmpty() &&
                    skills.getText().isEmpty() && healthUnitID.getText().isEmpty() && description.getText().isEmpty() && roleSelected.getText().isEmpty())){
                if (profileUrl != null){
                    File file = new File(profileUrl);
                    JSONObject userObj = new JSONObject();
                    userObj.put("userId", id.getText());
                    userObj.put("firstName", fName.getText());
                    userObj.put("middleName",mName.getText());
                    userObj.put("sirName", sirName.getText());
                    userObj.put("email", email.getText());
                    userObj.put("phoneNumber", phoneNo.getText());
                    userObj.put("dob", dob.getValue().toString() );
                    userObj.put("gender", gender.getValue().toString());
                    userObj.put("nationality", nationality.getText());

                    JSONObject practitionerObj = new JSONObject();
                    practitionerObj.put("practitionerId", prctitionerId.getText());
                    practitionerObj.put("userId", id.getText());
                    practitionerObj.put("title", title.getText());
                    practitionerObj.put("skill", skills.getText());
                    practitionerObj.put("description", description.getText());
                    practitionerObj.put("healthUnitId", Integer.parseInt(healthUnitID.getText()));
                    practitionerObj.put("user", null);
                    practitionerObj.put("healthUnit", null);

                   Practitioner uploaded= practitionerService.uploadPractitionerRegistration(practitionerObj,userObj, file);
                   if (uploaded != null){
                       uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Successfully uploaded","Confirmation",null, null).show();
                   }else
                       uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Cloud not upload the Information","Error",null, null).show();
                }else
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Please Select a profile Image","Error", null,null).show();

            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Provide all the Information Required in the Fields","Empty Fields","Error", null).show();
        } catch (Exception e) {
            logger.error("  "+e.getMessage());
            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Provide all the Information Required in the Fields","Empty Fields","Error", null).show();
        }
    }

    @FXML
    public void getHealthPractitioner(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER) && !(practitionerSearch.getText().isEmpty())){
           Practitioner practitioner = practitionerService.getPractitionerFromCloud(practitionerSearch.getText());
           //display the practitioner details
            displayPractitioner(practitioner);
        }

    }
    public void displayPractitioner(Practitioner practitioner){
        id.setText(practitioner.getUserId());
        fName.setText(practitioner.getUser().getFirstName());
        sirName.setText(practitioner.getUser().getSirName());
        mName.setText(practitioner.getUser().getMiddleName());
        email.setText(practitioner.getUser().getEmail());
        phoneNo.setText(practitioner.getUser().getPhoneNumber());
        dob.setValue(LocalDate.parse(practitioner.getUser().getDob()));
        if (practitioner.getUser().getGender().equalsIgnoreCase("male")) {
            gender.setValue(Gender.valueOf("Male"));
        }else
            gender.setValue(Gender.valueOf("Female"));

        title.setText(practitioner.getTitle());
        prctitionerId.setText(practitioner.getPractitionerId());
        nationality.setText(practitioner.getUser().getNationality());
        skills.setText(practitioner.getSkill());
        healthUnitID.setText(""+practitioner.getHealthUnit().getHealthUnitId());
        healthUnitName.setText(practitioner.getHealthUnit().getHealthUnitName());
        description.setText(practitioner.getDescription());
        //disable edit i all fields
        editBtn.setDisable(false);
        disableEditing();

    }
    @FXML
    public void enableEditing(){
        id.setDisable(false);
        fName.setDisable(false);
        sirName.setDisable(false);
        mName.setDisable(false);
        email.setDisable(false);
        phoneNo.setDisable(false);
        dob.setDisable(false);
        gender.setDisable(false);
        title.setDisable(false);
        prctitionerId.setDisable(false);
        nationality.setDisable(false);
        skills.setDisable(false);
        healthUnitID.setDisable(false);
        healthUnitName.setDisable(false);
        description.setDisable(false);
        roleSelected.setDisable(false);
        role.setDisable(false);
    }
    public void disableEditing(){
        id.setDisable(true);
        fName.setDisable(true);
        sirName.setDisable(true);
        mName.setDisable(true);
        email.setDisable(true);
        phoneNo.setDisable(true);
        dob.setDisable(true);
        gender.setDisable(true);
        title.setDisable(true);
        prctitionerId.setDisable(true);
        nationality.setDisable(true);
        skills.setDisable(true);
        healthUnitID.setDisable(true);
        healthUnitName.setDisable(true);
        description.setDisable(true);
        roleSelected.setDisable(true);
        role.setDisable(true);
    }
}
