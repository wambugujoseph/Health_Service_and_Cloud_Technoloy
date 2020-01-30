package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.HealthRecord;
import com.cloudHealth.desktopapp.model.MedicalRecord;
import com.cloudHealth.desktopapp.model.Patient;
import com.cloudHealth.desktopapp.model.User;
import com.cloudHealth.desktopapp.service.PatientProfileServiceImpl;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/23/2020
 * Time: 5:34 PM
 * Project: desktop-app
 */

@Component
public class AddPatientMedicalRecordController implements Initializable {

    @FXML
    private TextField userSearchTextField;

    @FXML
    private JFXButton userSearchBtn;

    @FXML
    private AnchorPane consultationPatientDetailsPane;

    @FXML
    private JFXTextArea consultationDescription;

    @FXML
    private JFXTextArea consultationType;

    @FXML
    private JFXTextField consultationDate;

    @FXML
    private JFXButton consultationClearBtn;

    @FXML
    private JFXButton consultationUploadBtn;

    @FXML
    private ButtonBar btnBar1;

    @FXML
    private AnchorPane filePatientDetailsPane;

    @FXML
    private JFXTextField fileDate;

    @FXML
    private CheckBox renameFileCheckBox;

    @FXML
    private JFXTextField fileLocationUri;

    @FXML
    private Button chooseFileBtn;

    @FXML
    private JFXTextField newFileName;

    @FXML
    private JFXTextArea fileDescription;

    @FXML
    private JFXTextArea fileType;

    @FXML
    private JFXButton fileClearFieldsBtn;

    @FXML
    private JFXButton fileUploadBtn;

    @FXML
    private ButtonBar btnBar2;

    @FXML
    private AnchorPane surgeryPatientDetailsPane;

    @FXML
    private JFXTextArea surgeryDescription;

    @FXML
    private JFXTextArea surgeryType;

    @FXML
    private JFXTextField surgeryDate;

    @FXML
    private JFXButton surgeryClearFieldsBtn;

    @FXML
    private JFXButton surgeryUploadBtn;

    @FXML
    private ButtonBar btnBar3;

    @FXML
    private AnchorPane ailmentPatientDetailsPane;

    @FXML
    private JFXTextArea ailmentDescription;

    @FXML
    private JFXTextArea ailmentType;

    @FXML
    private JFXTextField ailmentDate;

    @FXML
    private JFXButton ailmentClearFieldsBtn;

    @FXML
    private JFXButton ailmentUploadBtn;

    @FXML
    private ButtonBar btnBar4;

    @FXML
    private Tab prescriptionPatientDetailsPane;

    @FXML
    private JFXTextArea prescriptionDescription;

    @FXML
    private JFXTextArea prescriptionType;

    @FXML
    private JFXTextField prescriptionDate;

    @FXML
    private JFXButton prescriptionClearFieldsBtn;

    @FXML
    private JFXButton prescriptionUploadBtn;

    @FXML
    private ButtonBar btnBar5;

    @Autowired
    private PatientProfileServiceImpl patientProfileService;
    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;

    private Resource patientDetailsHolderAnchorPane;
    private ApplicationContext ac;

    Logger logger = LoggerFactory.getLogger(AddPatientMedicalRecordController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    AddPatientMedicalRecordController(
            ApplicationContext ac,
            @Value("${classpath:/templates/helperLayout/patientDetailsHolder.fxml}")Resource patientDetailsHolderAnchorPane){
        this.patientDetailsHolderAnchorPane = patientDetailsHolderAnchorPane;
        this.ac = ac;
    }

    @FXML
    public void isPatientCloudAvailable(){

        if (!(userSearchTextField.getText().isEmpty())){
            String userIdOrEmail = userSearchTextField.getText();
            Patient patient = patientProfileService.getPatient(userIdOrEmail);
            if (patient.getPatientId()>0) {
               setPatientInfo(patient);
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION,"Client you are loking for is not registered","Information",
                        null, null).show();
        }else
            userSearchTextField.setStyle("-fx-border-color: #ff2d3e");
    }

    private void setPatientInfo(Patient patient){
        User user = patient.getUserByUser();
//        logger.info("UserName"+ user.getFirstName());
        AnchorPane anchorPane;
        try {
            URL url = this.patientDetailsHolderAnchorPane.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();

            MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.VERIFIED);
            icon.setStyle("-fx-background-color: #00AE00");
            icon.setSize("60");
            Alert alert = uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION, "Would you like to start new Mediacl Record", "Confimation",
                    "Client is Available", icon);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.OK) {


                    Label userId = (Label) anchorPane.lookup("#userId");
                    Label fName = (Label) anchorPane.lookup("#fName");
                    Label mName = (Label) anchorPane.lookup("#mName");
                    Label sName = (Label) anchorPane.lookup("#sName");
                    Label gender = (Label) anchorPane.lookup("#gender");
                    Label dob = (Label) anchorPane.lookup("#dob");
                    Label phoneNo = (Label) anchorPane.lookup("#phoneNo");
                    Label email = (Label) anchorPane.lookup("#email");
                    Label nationality = (Label) anchorPane.lookup("#nationality");
                    Label patientId = (Label) anchorPane.lookup("#patientId");
                    Label recordId = (Label) anchorPane.lookup("#recordID");

                    userId.setText(user.getUserId());
                    fName.setText((user.getFirstName()));
                    mName.setText(user.getMiddleName());
                    sName.setText(user.getSirName());
                    gender.setText(user.getGender());
                    dob.setText(user.getDob());
                    phoneNo.setText(user.getPhoneNumber());
                    email.setText(user.getEmail());
                    nationality.setText(user.getNationality());
                    patientId.setText(patient.getPatientId()+"");
                    recordId.setText("");

                    consultationPatientDetailsPane.getChildren().add(anchorPane);
                    setRecordingDate();
                } else {

                }
            }


        } catch (IOException e) {
            logger.error("Error loading patientDetailsHolderAnchorPane", e.getStackTrace());
            Text text  = new Text("Whoops! \n\n The patient Is not Available");
            anchorPane = new AnchorPane(text);
            consultationPatientDetailsPane.getChildren().add(anchorPane);
        }

    }

    private void setRecordingDate(){
        String date = LocalDate.now().toString();
        prescriptionDate.setText(date);
        consultationDate.setText(date);
        fileDate.setText(date);
        ailmentDate.setText(date);
        surgeryDate.setText(date);
    }

    private MedicalRecord CreateNewMedicalRecord(){
        HealthRecord healthRecord =  new HealthRecord();
        healthRecord.setClientId();
    }
}
