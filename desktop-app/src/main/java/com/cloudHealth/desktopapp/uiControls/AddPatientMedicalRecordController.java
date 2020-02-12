package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.*;
import com.cloudHealth.desktopapp.service.AuthorizeUserService;
import com.cloudHealth.desktopapp.service.MedicalRecordServiceImpl;
import com.cloudHealth.desktopapp.service.PatientProfileServiceImpl;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import com.cloudHealth.desktopapp.util.FileUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
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
    private Tab consultationTab;
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
    private Tab filesTab;
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
    private Tab surgeryTab;
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
    private Tab ailmentTab;
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
    private Tab prescripionTab;
    @FXML
    private AnchorPane prespPatientDetailsPane;
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
    @Autowired
    private MedicalRecordServiceImpl medicalRecordService;
    @Autowired
    private AuthorizeUserService authorizeUserService;

    private Resource patientDetailsHolderAnchorPane;
    private User client;
    private ApplicationContext ac;
    private MedicalRecord medicalRecord;
    private AnchorPane anchorPane = new AnchorPane();

    Logger logger = LoggerFactory.getLogger(AddPatientMedicalRecordController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        init();
        tabActionChangeListener();
    }

    AddPatientMedicalRecordController(
            ApplicationContext ac,
            @Value("${classpath:/templates/helperLayout/patientDetailsHolder.fxml}")Resource patientDetailsHolderAnchorPane){
        this.patientDetailsHolderAnchorPane = patientDetailsHolderAnchorPane;
        this.ac = ac;
    }

    private void init(){
        newFileName.setDisable(true);
    }

    @FXML
    public void isPatientCloudAvailable(){

        if (!(userSearchTextField.getText().isEmpty())){
            String userIdOrEmail = userSearchTextField.getText();
            Patient patient = patientProfileService.getPatient(userIdOrEmail);
            if (patient.getPatientId()>0) {
               setPatientInfo(patient);
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION,"Client you are looking for is not registered \n" +
                                "Please create a profile for the patient","Information",
                        null, null).show();
        }else
            userSearchTextField.setStyle("-fx-border-color: #ff2d3e");
    }

    @FXML
    public void uploadConsultationAndProgressNotes(){
        if (!(consultationDescription.getText().isEmpty() && consultationType.getText().isEmpty())){
            Consultation consultation = new Consultation();
            consultation.setCreated(Date.valueOf(LocalDate.now()));
            consultation.setDescrption(consultationDescription.getText());
            consultation.setMedicalRecord(this.medicalRecord);
            consultation.setType(consultationType.getText());
            consultation.setRecordId(this.medicalRecord.getRecordId());
            Consultation tempConsultation = medicalRecordService.uploadConsultation(consultation);
            if (tempConsultation.getConsultationId() > 0){
                uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION,"Successfully Uploaded","Confirmation",null, null).show();
            }
        }else
            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Fields empty. All fields should be filled","ERROR", "Missing Information",null).show();

    }

    @FXML
    public void uploadSurgeryEvent(){
        if (!(surgeryDescription.getText().isEmpty() && surgeryType.getText().isEmpty())){
            Surgery surgery = new Surgery();
            surgery.setCreated(Date.valueOf(LocalDate.now()));
            surgery.setDescrption(surgeryDescription.getText());
            surgery.setType(surgeryType.getText());
            surgery.setMedicalRecord(this.medicalRecord);
            surgery.setRecordId(this.medicalRecord.getRecordId());
            Surgery tempSurgery = medicalRecordService.uploadSurgery(surgery);
            if (tempSurgery.getSurgeryId() > 0){
                uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION,"Successfully Uploaded","Confirmation",null, null).show();
            }
        }else
            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Fields empty. All fields should be filled","ERROR", "Missing Information",null).show();
    }

    @FXML
    public void uploadAilmentEvent(){
        if (!(ailmentDescription.getText().isEmpty() && ailmentType.getText().isEmpty())){
            Ailment ailment = new Ailment();
            ailment.setCreated(Date.valueOf(LocalDate.now()));
            ailment.setDescription(ailmentDescription.getText());
            ailment.setType(ailmentType.getText());
            ailment.setMedicalRecord(medicalRecord);
            ailment.setRecordId(medicalRecord.getRecordId());
           // logger.info("RecordId========== :" + medicalRecord.getRecordId());
            Ailment tempAilment =  medicalRecordService.uploadAilment(ailment);
            if (tempAilment.getAilmentId() >0){
                uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION,"Successfully Uploaded","Confirmation",null, null).show();
            }
        }else
            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Fields empty. All fields should be filled","ERROR", "Missing Information",null).show();

    }

    @FXML
    public void uploadPrescription(){
        if(!(prescriptionDescription.getText().isEmpty() && prescriptionType.getText().isEmpty())){
            Prescription prescription =  new Prescription();
            prescription.setCreated(Date.valueOf(LocalDate.now()));
            prescription.setDescrption(prescriptionDescription.getText());
            prescription.setMedication(prescriptionType.getText());
            prescription.setMedicalRecord(this.medicalRecord);
            prescription.setRecordId(this.medicalRecord.getRecordId());
            Prescription tempPrescription = medicalRecordService.uploadPrescriptionAndMedication(prescription);
            if (tempPrescription.getRecordId()>0)
                uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION,"Successfully Uploaded","Confirmation",null, null).show();
        }else
            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,"Fields empty. All fields should be filled","ERROR", "Missing Information",null).show();
    }

    @FXML
    public  void renameFileCheckBoxEvent(){
        if (renameFileCheckBox.isSelected()){
            newFileName.setDisable(false);
        }else
            newFileName.setDisable(true);
    }

    @FXML
    public void uploadMedicalFile() throws IOException {

        if (!(fileLocationUri.getText().isEmpty() && fileType.getText().isEmpty() && fileDescription.getText().isEmpty())) {
            File file = new File(fileLocationUri.getText());

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            FileSystemResource fileSystemResource;

            if (!(newFileName.getText().isEmpty())){
                fileSystemResource =  new FileSystemResource(FileUtil.renameFile(file,newFileName.getText()));
            }else
                fileSystemResource = new FileSystemResource(file);

            body.add("file", fileSystemResource);
            body.add("description", fileDescription.getText());
            body.add("recordType", fileType.getText());
            body.add("medicalRecordId", this.medicalRecord.getRecordId());
            body.add("userId", this.client.getUserId());
            MedicalFile medicalFile1 = medicalRecordService.uploadMedicalFile(body);
            //logger.info("" + medicalFile1.toString());
            if (medicalFile1.getRecordId() == this.medicalRecord.getRecordId()){
                uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR, "File successfully uploaded", "CONFIRMATION", null, null).show();
            }

        } else
            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR, "Fields empty. All fields should be filled", "ERROR", "Missing Information", null).show();
    }

    @FXML
    public void medicalFileChooser(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(this.chooseFileBtn.getScene().getWindow());
        String url = file.getPath();
        fileLocationUri.setText(url);
    }

    private void setPatientInfo(Patient patient){
        client = patient.getUserByUser();
        //logger.info("UserName"+ user.getFirstName());;
        try {
            URL url = this.patientDetailsHolderAnchorPane.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            this.anchorPane = fxmlLoader.load();

            MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.VERIFIED);
            icon.setStyle("-fx-background-color: #00AE00");
            icon.setFill(Paint.valueOf("#000080"));
            icon.setSize("60");
            Alert alert = uiAlertsAndPopUp.showAlert(Alert.AlertType.CONFIRMATION, "Would you like to start new Medical Record", "Confirmation",
                    "Client is Available", icon);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    //create new medical record and return the Record
                    MedicalRecord tempMedicalRecord = createNewMedicalRecord(client);

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

                    userId.setText(client.getUserId());
                    fName.setText((client.getFirstName()));
                    mName.setText(client.getMiddleName());
                    sName.setText(client.getSirName());
                    gender.setText(client.getGender());
                    dob.setText(client.getDob());
                    phoneNo.setText(client.getPhoneNumber());
                    email.setText(client.getEmail());
                    nationality.setText(client.getNationality());
                    patientId.setText(patient.getPatientId()+"");
                    recordId.setText((tempMedicalRecord.getRecordId()+""));

                    consultationPatientDetailsPane.getChildren().add(anchorPane);
                    setRecordingDate();
                } else {

                }
            }


        } catch (IOException e) {
            logger.error("Error loading patientDetailsHolderAnchorPane :="+ e.getMessage());
            Text text  = new Text("Whoops! \n\n The patient Is not Available");
            AnchorPane anchorPane = new AnchorPane(text);
            consultationPatientDetailsPane.getChildren().add(anchorPane);
        }

    }

    private void tabActionChangeListener(){
        try {
            ailmentTab.setOnSelectionChanged(event -> {
                if (ailmentTab.isSelected())
                    ailmentPatientDetailsPane.getChildren().add(anchorPane);
            });
            consultationTab.setOnSelectionChanged(event -> {
                if (consultationTab.isSelected())
                    consultationPatientDetailsPane.getChildren().add(anchorPane);
            });
            filesTab.setOnSelectionChanged(event -> {
                if (filesTab.isSelected())
                    filePatientDetailsPane.getChildren().add(anchorPane);
            });
            surgeryTab.setOnSelectionChanged(event -> {
                if (surgeryTab.isSelected())
                    surgeryPatientDetailsPane.getChildren().add(anchorPane);
            });
            prescripionTab.setOnSelectionChanged(event -> {
                if (prescripionTab.isSelected())
                    prespPatientDetailsPane.getChildren().add(anchorPane);
            });

        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("Tab selection listener Error " + e.getMessage());
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

    private MedicalRecord createNewMedicalRecord(User user){

        try {
            Practitioner tempPractitioner = medicalRecordService.getPractitioner(authorizeUserService.getUserEmail());
//            logger.info(" email "+authorizeUserService.getUserEmail());
//            logger.info(" name "+tempPractitioner.getPractitionerId());
            HealthRecord healthRecord =  new HealthRecord();
            healthRecord.setClientId(user.getUserId());
            healthRecord.setHealthUnitName(tempPractitioner.getHealthUnit().getHealthUnitName());
            healthRecord.setPractitionerId(tempPractitioner.getPractitionerId());
            String desc = "Initial Record from "+healthRecord.getHealthUnitName();
            healthRecord.setDescription(desc);

            medicalRecord = medicalRecordService.createMedicalRecord(healthRecord);
//            logger.info(medicalRecord.toString());

            return medicalRecord;
        } catch (Exception e) {
            logger.error("Failed to create medical record == "+e.getMessage());
            return new MedicalRecord();
        }

    }
}
