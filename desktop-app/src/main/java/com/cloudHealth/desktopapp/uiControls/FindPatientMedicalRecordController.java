package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.*;
import com.cloudHealth.desktopapp.service.MedicalRecordServiceImpl;
import com.cloudHealth.desktopapp.service.PatientProfileServiceImpl;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import com.cloudHealth.desktopapp.util.FileUtil;
import com.cloudHealth.desktopapp.util.StringFormatter;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/23/2020
 * Time: 12:18 AM
 * Project: desktop-app
 */

@Component
public class FindPatientMedicalRecordController implements Initializable {

    @FXML private JFXHamburger medicalRecordHamburger;
    @FXML private JFXDrawer medicalRecordDrawer;
    @FXML private TextField medicalRecordSearch;
    @FXML private JFXButton newConsultation;
    @FXML private JFXButton newMedicalRecordConsultation;
    @FXML private Label patientConsultationName;
    @FXML private Label patientConsultationEmail;
    @FXML private Label patientConsultationPhone;
    @FXML private Label patientConsultationID;
    @FXML private ScrollPane consultationScrollPane;
    @FXML private Text newFileRecord;
    @FXML private JFXButton newMedicalFileRecord;
    @FXML private JFXButton newMedicalRecordFile;
    @FXML private Label medicalFilePatientName;
    @FXML private Label medicalFilePatientEmail;
    @FXML private Label medicalFilePatientPhone;
    @FXML private Label medicalFilePatientID;
    @FXML private ScrollPane medicalFileScrollPane;
    @FXML private JFXButton newAilment;
    @FXML private JFXButton newMedicalRecordAilment;
    @FXML private Label patientAilmentName;
    @FXML private Label patientAilmentEmail;
    @FXML private Label patientAilmentPhone;
    @FXML private Label patientAilmentID;
    @FXML private ScrollPane ailmentScrollPane;
    @FXML private JFXButton newPrescription;
    @FXML private JFXButton newMedicalRecordPrescription;
    @FXML private Label PatientPrescriptionName;
    @FXML private Label PatientPrescriptionEmail;
    @FXML private Label PatientPrescriptionPhone;
    @FXML private Label PatientPrescriptionID;
    @FXML private ScrollPane prescriptionScrollPane;
    @FXML private JFXButton newSurgeryRecord;
    @FXML private JFXButton newMedicalRecordSurgery;
    @FXML private Label patientSurgeryName;
    @FXML private Label patientSurgeryEmail;
    @FXML private Label patientSurgeryPhone;
    @FXML private Label patientSurgeryID;
    @FXML private ScrollPane surgeryScrollPane;
    @FXML private VBox consultationVBoxView;
    @FXML private VBox ailmentVBoxView;
    @FXML private VBox prescriptionVBoxView;
    @FXML private VBox surgeryVBoxView;
    @FXML private Label patientRealTimeName;
    @FXML private Label patientRealTimeEmail;
    @FXML private Label patientRealTimePhone;
    @FXML private Label patientRealTimeId;
    @FXML private AnchorPane realTimeDisplayAnchor;
    @FXML private JFXButton realtimeRefreshBtn;
    @FXML private JFXButton realTimeStopBtn;
    @FXML private TableView<TempRealTimeData> realTimeRecordTable;
    @FXML private TableColumn<TempRealTimeData, Integer> count;
    @FXML private TableColumn<TempRealTimeData, String> id;
    @FXML private TableColumn<TempRealTimeData, String> patientId;
    @FXML private TableColumn<TempRealTimeData, String> recordType;
    @FXML private TableColumn<TempRealTimeData, String> dateTime;
    @FXML private TableColumn<TempRealTimeData, GeoLocation> latitude;
    @FXML private TableColumn<TempRealTimeData, GeoLocation> longitude;
    @FXML private TableColumn<TempRealTimeData, String> value;
    @FXML private VBox medicalFileVboxView;
    @FXML private StackPane consultationStackPane;
    @FXML private StackPane medicalFilesStackPane;
    @FXML private StackPane ailmentStackpane;
    @FXML private StackPane prscriptionStackPane;
    @FXML private StackPane surgeryStackPane;



    @Autowired
    private MedicalRecordServiceImpl medicalRecordService;
    @Autowired
    private PatientProfileServiceImpl patientProfileService;
    @Autowired
    private HomeController homeController;
    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;

    private JFXListView listView = new JFXListView();
    private Resource consultationContentDisplay, ailmentContentDisplay, prescriptionContentDisplay,
            surgeryContentDisplay,medicalFileDisplay,realTimeDisplay, newConsultationRecord, newMedicalFileAdd,newAilmentAdd, newPrescriptionAdd,
            newSurgeryAdd;
    private ApplicationContext ac;
    private MedicalRecord[] medicalRecords;
    private int medicalRecordId = 0;
    private JFXDialog dialog;
    Logger logger = LoggerFactory.getLogger(FindPatientMedicalRecordController.class);

    private Collection<MedicalRecord> medicalRecordCollection = new ArrayList<>();

    FindPatientMedicalRecordController(@Value("${classpath:/templates/helperLayout/ailmentDisplay.fxml}") Resource ailmentContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/consultationDisplay.fxml}") Resource consultationContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/prescriptionDisplay.fxml}") Resource prescriptionContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/surgeryDisplay.fxml}") Resource surgeryContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/medicalFileDisplay.fxml}") Resource medicalFileDisplay,
                                       @Value("${classpath:/templates/helperLayout/realtimeRecord.fxml}") Resource realTimeDisplay,
                                       @Value("${classpath:/templates/helperLayout/newConsultation.fxml}") Resource newConsultationRecord,
                                       @Value("${classpath:/templates/helperLayout/newMedicalFile.fxml}") Resource newMedicalFileAdd,
                                       @Value("${classpath:/templates/helperLayout/newAilment.fxml}") Resource newAilmentAdd,
                                       @Value("${classpath:/templates/helperLayout/newPrescription.fxml}") Resource newPrescriptionAdd,
                                       @Value("${classpath:/templates/helperLayout/newSurgery.fxml}") Resource newSurgeryAdd,
                                       ApplicationContext ac) {
        this.consultationContentDisplay = consultationContentDisplay;
        this.ailmentContentDisplay = ailmentContentDisplay;
        this.prescriptionContentDisplay = prescriptionContentDisplay;
        this.surgeryContentDisplay = surgeryContentDisplay;
        this.medicalFileDisplay = medicalFileDisplay;
        this.realTimeDisplay = realTimeDisplay;
        this.newConsultationRecord = newConsultationRecord;
        this.newMedicalFileAdd = newMedicalFileAdd;
        this.newAilmentAdd = newAilmentAdd;
        this.newPrescriptionAdd = newPrescriptionAdd;
        this.newSurgeryAdd = newSurgeryAdd;
        this.ac = ac;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventActionEvents();
        startNewMedicalRecord();
    }

    private void patientRecordDrawerOpenClose(HamburgerBackArrowBasicTransition backArrow) {

        medicalRecordHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            backArrow.setRate(backArrow.getRate() * -1);
            backArrow.play();
            if (medicalRecordDrawer.isShown())
                medicalRecordDrawer.close();
            else
                medicalRecordDrawer.open();
        });
    }

    private void startNewMedicalRecord(){
        newMedicalRecordConsultation.setOnAction(event -> newMedicalRecordStarter());
        newMedicalRecordAilment.setOnAction(event -> newMedicalRecordStarter());
        newMedicalRecordFile.setOnAction(event -> newMedicalRecordStarter());
        newMedicalRecordAilment.setOnAction(event -> newMedicalRecordStarter());
        newMedicalRecordPrescription.setOnAction(event -> newMedicalRecordStarter());
        newMedicalRecordSurgery.setOnAction(event -> newMedicalRecordStarter());

    }

    private void newMedicalRecordStarter() {
        try {
            homeController.setAddRecordsUi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eventActionEvents() {
        HamburgerBackArrowBasicTransition backArrow = new HamburgerBackArrowBasicTransition(medicalRecordHamburger);
        backArrow.setRate(-1);
        patientRecordDrawerOpenClose(backArrow);
        setMedicalRecordSearch();
        drawerListVieEventListener();
        newMedicalRecords();
    }

    private void drawerListVieEventListener(){
        this.listView.setOnMouseClicked(event -> chooseDifferentMedicalRecord() );
        this.listView.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)){
                chooseDifferentMedicalRecord();
            }
        });
    }
    private void setMedicalRecordSearch() {
        medicalRecordSearch.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (!medicalRecordSearch.getText().isEmpty()) {
                    String userIdOrEmail = medicalRecordSearch.getText();
                   this.medicalRecords = medicalRecordService.getAllPatientMedicalRecords(userIdOrEmail);
                    populatedDrawerContent(this.medicalRecords);
                    for (MedicalRecord medicalRecord : this.medicalRecords) {
                        displayMedicalRecord(medicalRecord);
                    }
                }
            }
        });
    }

    private void newMedicalRecords(){
        newConsultation.setOnAction(event -> {
            if (medicalRecordId != 0) {
                getNewConsultationRecordEntry();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.WARNING,"No selected Medical Record","Information",null,null).show();
        });
        newMedicalFileRecord.setOnAction(event -> {
            if (medicalRecordId != 0) {
                getNewMedicalFileEntry();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.WARNING,"No selected Medical Record","Information",null,null).show();
        });
        newAilment.setOnAction(event -> {
            if (medicalRecordId != 0) {
                getNewAilmentRecordEntry();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.WARNING,"No selected Medical Record","Information",null,null).show();
        });
        newPrescription.setOnAction(event -> {
            if (medicalRecordId != 0) {
                getNewPrescriptionRecordEntry();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.WARNING,"No selected Medical Record","Information",null,null).show();
        });
        newSurgeryRecord.setOnAction(event -> {
            if (medicalRecordId != 0) {
                getNewSurgeryRecordEntry();
            }else
                uiAlertsAndPopUp.showAlert(Alert.AlertType.WARNING,"No selected Medical Record","Information",null,null).show();
        });
    }
    private void chooseDifferentMedicalRecord() {
        String selectedItemText = listView.getSelectionModel().getSelectedItem().toString();
        //logger.info("The selected item text is :" + selectedItemText);
        for (MedicalRecord medicalRecord: this.medicalRecords){
            //check if the selected listItem contain setMedical id
            if (selectedItemText.contains(medicalRecord.getRecordId()+"")){
                medicalRecordId= medicalRecord.getRecordId();
                //display the selected record
                try {

                    consultationVBoxView.getChildren().setAll(new Text(""));
                    consultationVBoxView.getChildren().remove(0);
                    ailmentVBoxView.getChildren().setAll(new Text(""));
                    ailmentVBoxView.getChildren().remove(0);
                    prescriptionVBoxView.getChildren().setAll(new Text(""));
                    prescriptionVBoxView.getChildren().remove(0);
                    surgeryVBoxView.getChildren().setAll(new Text(""));
                    surgeryVBoxView.getChildren().remove(0);
                    medicalFileVboxView.getChildren().setAll(new Text(""));
                    medicalFileVboxView.getChildren().remove(0);
                } catch (IndexOutOfBoundsException e) {
                   logger.info("chooseDifferentMedicalRecord method IndexOutOfBoundsException "+e.getMessage());
                }
                displayMedicalRecord(medicalRecord);
                //logger.info("Record id"+ medicalRecord.getRecordId());
            }
        }

    }

    private void populatedDrawerContent(MedicalRecord[] medicalRecords) {

        ObservableList<String> list = FXCollections.observableArrayList();
        for (MedicalRecord medicalRecord : medicalRecords) {
            list.add(medicalRecord.getCreated().toString()+ ", Record Id = "+medicalRecord.getRecordId() +" \n"+ medicalRecord.getDescription()+
                    " \nPractitioner Id = "+medicalRecord.getPractitionerId());
        }
        this.listView.setItems(list);
        this.listView.setVerticalGap(5.0);
        this.listView.setBorder(Border.EMPTY);
        this.listView.prefWidth(medicalRecordDrawer.getWidth());

        medicalRecordDrawer.setSidePane(this.listView);
    }

    private void displayMedicalRecord(MedicalRecord medicalRecord){
        //get display Areas
        Collection<Consultation> consultations = medicalRecord.getConsultations();
        displayMedicalConsultation(consultations);
        Collection<Ailment> ailments = medicalRecord.getAilments();
        displayMedicalAilment(ailments);
        Collection<Prescription> prescriptions = medicalRecord.getPrescriptions();
        displayMedicalPrescription(prescriptions);
        Collection<Surgery> surgeries = medicalRecord.getSurgeries();
        displayMedicalSurgery(surgeries);
        Collection<MedicalFile> medicalFiles = medicalRecord.getMedicalFiles();
        //logger.info("Size ==== "+medicalFiles.size());
        displayMedicalFile(medicalFiles);

        displayPatientDetails(medicalRecord.getPatientId());
        displayRealTimeRecord();

    }

    private void displayMedicalConsultation(Collection<Consultation> consultations) {
        ObservableList<AnchorPane> consultationAnchorPanes = FXCollections.observableArrayList();

        for (Consultation consultation: consultations) {
            AnchorPane displayAnchorPane = getConsultationDisplayHolder();
            Label dateLabel = (Label) displayAnchorPane.lookup("#dateLabel");
            Text typeTextDisplay = (Text) displayAnchorPane.lookup("#typeDisplay");
            Text descriptionTxtDisplay = (Text) displayAnchorPane.lookup("#descriptionDisplay");
            Label patientId =(Label) displayAnchorPane.lookup("#patientId");
            Label recordId = (Label) displayAnchorPane.lookup("#recordId");
            Label consultationId = (Label) displayAnchorPane.lookup("#consultationId");
            dateLabel.setText(String.valueOf(consultation.getCreated().toLocalDate()));
            typeTextDisplay.setText(consultation.getType());
            descriptionTxtDisplay.setText(consultation.getDescrption());
            patientId.setText(this.medicalRecords[0].getRecordId()+"");
            recordId.setText(consultation.getRecordId()+"");
            consultationId.setText(consultation.getConsultationId()+"");
            consultationAnchorPanes.add(displayAnchorPane);
        }
        consultationVBoxView.getChildren().addAll(consultationAnchorPanes);
    }

    private void displayMedicalAilment(Collection<Ailment> ailments){

        ObservableList<AnchorPane> ailmentAnchorPanes = FXCollections.observableArrayList();
        for (Ailment ailment: ailments) {
            AnchorPane displayAnchorPane = getAilmentDisplayHolder();
            Label dateLabel = (Label) displayAnchorPane.lookup("#ailmentDateLabel");
            Text typeTextDisplay = (Text) displayAnchorPane.lookup("#ailmentTypeDisplay");
            Text descriptionTxtDisplay = (Text) displayAnchorPane.lookup("#ailmentDescriptionDisplay");
            typeTextDisplay.setText(ailment.getType());
            descriptionTxtDisplay.setText(ailment.getDescription());
            dateLabel.setText(String.valueOf(ailment.getCreated().toLocalDate()));
            ailmentAnchorPanes.add(displayAnchorPane);
        }
        ailmentVBoxView.getChildren().addAll(ailmentAnchorPanes);

    }

    private void displayMedicalPrescription(Collection<Prescription> prescriptions){

        ObservableList<AnchorPane> prescriptionAnchorPanes = FXCollections.observableArrayList();
        for (Prescription prescription: prescriptions) {
            AnchorPane displayAnchorPane = getPrescriptionDisplayHolder();
            Label dateLabel = (Label) displayAnchorPane.lookup("#prescriptionDateLabel");
            Text typeTextDisplay = (Text) displayAnchorPane.lookup("#prescriptonTypeDisplay");
            Text descriptionTxtDisplay = (Text) displayAnchorPane.lookup("#precriptionDescriptionDisplay");
            dateLabel.setText(String.valueOf(prescription.getCreated().toLocalDate()));
            typeTextDisplay.setText(prescription.getMedication());
            descriptionTxtDisplay.setText(prescription.getDescrption());
            prescriptionAnchorPanes.add(displayAnchorPane);
        }
        prescriptionVBoxView.getChildren().addAll(prescriptionAnchorPanes);

    }

    private void displayMedicalSurgery(Collection<Surgery> surgeries){

        ObservableList<AnchorPane> surgeryAnchorPanes = FXCollections.observableArrayList();
        for (Surgery surgery: surgeries) {
            AnchorPane displayAnchorPane = getSurgeryDisplayHolder();
            Label dateLabel = (Label) displayAnchorPane.lookup("#surgeryDateLabel");
            Text typeTextDisplay = (Text) displayAnchorPane.lookup("#surgeryTypeDisplay");
            Text descriptionTxtDisplay = (Text) displayAnchorPane.lookup("#surgeryDescriptionDisplay");
            dateLabel.setText(String.valueOf(surgery.getCreated().toLocalDate()));
            typeTextDisplay.setText(surgery.getDescrption());
            descriptionTxtDisplay.setText(surgery.getDescrption());
            surgeryAnchorPanes.add(displayAnchorPane);
        }
        surgeryVBoxView.getChildren().addAll(surgeryAnchorPanes);

    }

    private void displayMedicalFile(Collection<MedicalFile> medicalFiles){
        ObservableList<AnchorPane> medicalFileAnchorPanes = FXCollections.observableArrayList();
        for (MedicalFile medicalFile : medicalFiles) {
            AnchorPane displayAnchorPane = getFileDisplayHolder();
            Label dateLabel = (Label) displayAnchorPane.lookup("#medicalFileDateLabel");
            Text fileTypeTextDisplay = (Text) displayAnchorPane.lookup("#fileTypeDisplay");
            Text descriptionTxtDisplay = (Text) displayAnchorPane.lookup("#fileDescriptionDisplay");
            Label fileType = (Label) displayAnchorPane.lookup("#fileName");
            dateLabel.setText(medicalFile.getCreated().toString());
            fileTypeTextDisplay.setText(medicalFile.getRecordType());
            descriptionTxtDisplay.setText(medicalFile.getDescrption());
            fileType.setText(medicalFile.getFileUrl());
            medicalFileAnchorPanes.add(displayAnchorPane);
        }
        medicalFileVboxView.getChildren().addAll(medicalFileAnchorPanes);
    }

    @FXML
    private void getObj(){
        logger.info("Object clicked ;" +medicalFileVboxView.getChildren().size());
    }

    private void displayPatientDetails(int patientId) {
        Platform.runLater(() -> {
            Patient patient = patientProfileService.getPatient(patientId);
            User user = patient.getUserByUser();
            String name = StringFormatter.capitalizeWord(user.getFirstName() + " " + user.getSirName());
            patientConsultationName.setText(name);
            medicalFilePatientName.setText(name);
            patientSurgeryName.setText(name);
            PatientPrescriptionName.setText(name);
            patientAilmentName.setText(name);

            patientConsultationEmail.setText(user.getEmail());
            medicalFilePatientEmail.setText(user.getEmail());
            patientSurgeryEmail.setText(user.getEmail());
            PatientPrescriptionEmail.setText(user.getEmail());
            patientAilmentEmail.setText(user.getEmail());

            patientConsultationPhone.setText(user.getPhoneNumber());
            medicalFilePatientPhone.setText(user.getPhoneNumber());
            patientSurgeryPhone.setText(user.getPhoneNumber());
            PatientPrescriptionPhone.setText(user.getPhoneNumber());
            patientAilmentPhone.setText(user.getPhoneNumber());

            patientConsultationID.setText(user.getUserId());
            medicalFilePatientID.setText(user.getUserId());
            patientSurgeryID.setText(user.getUserId());
            PatientPrescriptionID.setText(user.getUserId());
            patientAilmentID.setText(user.getUserId());

            patientRealTimeName.setText(name);
            patientRealTimeEmail.setText(user.getEmail());
            patientRealTimePhone.setText(user.getPhoneNumber());
            patientRealTimeId.setText(user.getUserId());
        });

    }

    private void displayRealTimeRecord(){
        //get the records
        RealTimeData[] realTimeDataList = medicalRecordService.getRealTimeRecord(this.medicalRecords[0].getPatientId()+"");
        //display the records
        ObservableList<TempRealTimeData> data = FXCollections.observableArrayList();
        int i =1;
        for(RealTimeData realTimeData : realTimeDataList){
            TempRealTimeData tempRealTimeData = new TempRealTimeData();
            tempRealTimeData.setCount(i);
            tempRealTimeData.setDateTime(realTimeData.getCreated().toString());
            tempRealTimeData.setId(realTimeData.getRealTimeId()+"");
            tempRealTimeData.setPatientId(realTimeData.getPatientId()+"");
            tempRealTimeData.setRecordType(realTimeData.getRecordType());
            tempRealTimeData.setValue(realTimeData.getValue());
            tempRealTimeData.setLatitude(realTimeData.getGeoLocationByLocationId().getLatitude()+"");
            tempRealTimeData.setLongitude(realTimeData.getGeoLocationByLocationId().getLongitude()+"");

            data.add(tempRealTimeData);
            i++;
        }

        this.count.setCellValueFactory(new PropertyValueFactory<>("count"));
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.patientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        this.recordType.setCellValueFactory(new PropertyValueFactory<>("recordType"));
        this.dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        this.latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        this.longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        this.value.setCellValueFactory(new PropertyValueFactory<>("value"));

        this.realTimeRecordTable.setItems(null);
        this.realTimeRecordTable.setItems(data);

    }

    private AnchorPane getConsultationDisplayHolder(){
        try {
            AnchorPane anchorPane;
            URL url = this.consultationContentDisplay.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            return anchorPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    private AnchorPane getAilmentDisplayHolder(){
        try {
            AnchorPane anchorPane;
            URL url = this.ailmentContentDisplay.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            return anchorPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    private AnchorPane getPrescriptionDisplayHolder(){
        try {
            AnchorPane anchorPane;
            URL url = this.prescriptionContentDisplay .getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            return anchorPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    private AnchorPane getSurgeryDisplayHolder(){
        try {
            AnchorPane anchorPane;
            URL url = this.surgeryContentDisplay .getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            return anchorPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    private AnchorPane getFileDisplayHolder(){
        try {
            AnchorPane anchorPane;
            URL url = this.medicalFileDisplay.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            return anchorPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    private void getNewConsultationRecordEntry(){
        try {
            AnchorPane anchorPane;
            URL url = this.newConsultationRecord.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            JFXDialogLayout content = new JFXDialogLayout();
            content.setBody(anchorPane);
            dialog = new JFXDialog( consultationStackPane, content, JFXDialog.DialogTransition.TOP);
            dialog.show();
            JFXButton closeBtn = (JFXButton) anchorPane.lookup("#close");
            dialog.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ESCAPE)){
                    dialog.close();
                }
            });
            closeBtn.setOnAction(e-> dialog.close());

            JFXTextArea description = (JFXTextArea) anchorPane.lookup("#description");
            JFXTextField type = (JFXTextField) anchorPane.lookup("#type");
            JFXButton upload = (JFXButton) anchorPane.lookup("#upload");

            upload.setOnAction(event -> {
                if (!(description.getText().isEmpty() && type.getText().isEmpty())){
                    Consultation consultation = new Consultation();
                    if (medicalRecordId !=0) {
                        consultation.setRecordId(medicalRecordId);
                        consultation.setType(type.getText());
                        for (MedicalRecord medicalRecord : medicalRecords) {
                            if (medicalRecord.getRecordId() == medicalRecordId)
                                consultation.setMedicalRecord(medicalRecord);
                        }
                        consultation.setConsultationId(0);
                        consultation.setDescrption(description.getText());
                        consultation.setCreated(Date.valueOf(LocalDate.now()));
                        try {
                            Consultation conslt = medicalRecordService.uploadConsultation(consultation);
                            if (conslt.getRecordId() == medicalRecordId){
                                uiAlertsAndPopUp.showAlert(
                                        Alert.AlertType.CONFIRMATION,"Uploaded Successfully","Confirmation",null,null
                                ).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,e.getMessage(),"Error",null,null).show();
                        }
                    }
                }else
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION,"No selected Medical record","Message",null, null).show();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getNewMedicalFileEntry(){
        try {
            AnchorPane anchorPane;
            URL newFleUrl = this.newMedicalFileAdd.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(newFleUrl);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            JFXDialogLayout content = new JFXDialogLayout();
            content.setBody(anchorPane);
            dialog = new JFXDialog( medicalFilesStackPane, content, JFXDialog.DialogTransition.CENTER);
            dialog.show();
            JFXButton closeBtn = (JFXButton) anchorPane.lookup("#close");
            dialog.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ESCAPE)){
                    dialog.close();
                }
            });
            closeBtn.setOnAction(e-> dialog.close());

            JFXTextArea description = (JFXTextArea) anchorPane.lookup("#description");
            JFXTextField fileLocation = (JFXTextField) anchorPane.lookup("#fileLocation");
            Button chooseFileBtn = (Button) anchorPane.lookup("#chooseFile");
            JFXTextArea fileType = (JFXTextArea) anchorPane.lookup("#type");
            JFXButton upload = (JFXButton) anchorPane.lookup("#upload");

            chooseFileBtn.setOnAction(event ->{
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(chooseFileBtn.getScene().getWindow());
                fileLocation.setText(file.getPath());
            });

            upload.setOnAction(event -> {
                if (!(description.getText().isEmpty() && fileType.getText().isEmpty() && fileLocation.getText().isEmpty())) {
                    MedicalFile medicalFile = new MedicalFile();
                    if (medicalRecordId != 0) {
                        medicalFile.setRecordType(fileType.getText());
                        for (MedicalRecord medicalRecord : medicalRecords) {
                            if (medicalRecord.getRecordId() == medicalRecordId)
                                medicalFile.setMedicalRecord(medicalRecord);
                        }
                        File file = new File(fileLocation.getText());
                        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                        FileSystemResource fileSystemResource = new FileSystemResource(file);

                        body.add("file", fileSystemResource);
                        body.add("description", description.getText());
                        body.add("recordType", fileType.getText());
                        body.add("medicalRecordId", this.medicalRecordId);
                       body.add("userId", this.medicalFilePatientID.getText());
                        try {
                            MedicalFile medFile = medicalRecordService.uploadMedicalFile(body);
                            if (medFile.getRecordId() == medicalRecordId) {
                                uiAlertsAndPopUp.showAlert(
                                        Alert.AlertType.CONFIRMATION, "Uploaded Successfully", "Confirmation", null, null
                                ).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR, e.getMessage(), "Error", null, null).show();
                        }
                    } else
                        uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION, "No selected Medical record", "Message", null, null).show();
                }else
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION, "Empty Fields", "Message", null, null).show();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getNewAilmentRecordEntry(){
        try {
            AnchorPane anchorPane;
            URL url = this.newAilmentAdd.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            JFXDialogLayout content = new JFXDialogLayout();
            content.setBody(anchorPane);
            dialog = new JFXDialog( ailmentStackpane, content, JFXDialog.DialogTransition.TOP);
            dialog.show();
            JFXButton closeBtn = (JFXButton) anchorPane.lookup("#close");
            dialog.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ESCAPE)){
                    dialog.close();
                }
            });
            closeBtn.setOnAction(e-> dialog.close());

            JFXTextArea description = (JFXTextArea) anchorPane.lookup("#description");
            JFXTextField type = (JFXTextField) anchorPane.lookup("#type");
            JFXButton upload = (JFXButton) anchorPane.lookup("#upload");

            upload.setOnAction(event -> {
                if (!(description.getText().isEmpty() && type.getText().isEmpty())){
                    Ailment ailment = new Ailment();
                    if (medicalRecordId !=0) {
                        ailment.setRecordId(medicalRecordId);
                        ailment.setType(type.getText());
                        for (MedicalRecord medicalRecord : medicalRecords) {
                            if (medicalRecord.getRecordId() == medicalRecordId)
                                ailment.setMedicalRecord(medicalRecord);
                        }
                        ailment.setAilmentId(0);
                        ailment.setDescription(description.getText());
                        ailment.setCreated(Date.valueOf(LocalDate.now()));
                        try {
                            Ailment almt = medicalRecordService.uploadAilment(ailment);
                            if (almt.getRecordId() == medicalRecordId){
                                uiAlertsAndPopUp.showAlert(
                                        Alert.AlertType.CONFIRMATION,"Uploaded Successfully","Confirmation",null,null
                                ).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,e.getMessage(),"Error",null,null).show();
                        }
                    }
                }else
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION,"No selected Medical record","Message",null, null).show();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getNewPrescriptionRecordEntry(){
        try {
            AnchorPane anchorPane;
            URL url = this.newPrescriptionAdd.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            JFXDialogLayout content = new JFXDialogLayout();
            content.setBody(anchorPane);
            dialog = new JFXDialog( prscriptionStackPane, content, JFXDialog.DialogTransition.BOTTOM);
            dialog.show();
            JFXButton closeBtn = (JFXButton) anchorPane.lookup("#close");
            dialog.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ESCAPE)){
                    dialog.close();
                }
            });
            closeBtn.setOnAction(e-> dialog.close());

            JFXTextArea description = (JFXTextArea) anchorPane.lookup("#description");
            JFXTextField type = (JFXTextField) anchorPane.lookup("#type");
            JFXButton upload = (JFXButton) anchorPane.lookup("#upload");

            upload.setOnAction(event -> {
                if (!(description.getText().isEmpty() && type.getText().isEmpty())){
                    Prescription prescription = new Prescription();
                    if (medicalRecordId !=0) {
                        prescription.setRecordId(medicalRecordId);
                        prescription.setMedication(type.getText());
                        for (MedicalRecord medicalRecord : medicalRecords) {
                            if (medicalRecord.getRecordId() == medicalRecordId)
                                prescription.setMedicalRecord(medicalRecord);
                        }
                        prescription.setPrecriptionId(0);
                        prescription.setDescrption(description.getText());
                        prescription.setCreated(Date.valueOf(LocalDate.now()));
                        try {
                            Prescription prescrip = medicalRecordService.uploadPrescriptionAndMedication(prescription);
                            if (prescrip.getRecordId() == medicalRecordId){
                                uiAlertsAndPopUp.showAlert(
                                        Alert.AlertType.CONFIRMATION,"Uploaded Successfully","Confirmation",null,null
                                ).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,e.getMessage(),"Error",null,null).show();
                        }
                    }
                }else
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION,"No selected Medical record","Message",null, null).show();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getNewSurgeryRecordEntry(){
        try {
            AnchorPane anchorPane;
            URL url = this.newSurgeryAdd.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            anchorPane = fxmlLoader.load();
            JFXDialogLayout content = new JFXDialogLayout();
            content.setBody(anchorPane);
            dialog = new JFXDialog( surgeryStackPane, content, JFXDialog.DialogTransition.BOTTOM);
            dialog.show();
            JFXButton closeBtn = (JFXButton) anchorPane.lookup("#close");
            dialog.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ESCAPE)){
                    dialog.close();
                }
            });
            closeBtn.setOnAction(e-> dialog.close());

            JFXTextArea description = (JFXTextArea) anchorPane.lookup("#description");
            JFXTextField type = (JFXTextField) anchorPane.lookup("#type");
            JFXButton upload = (JFXButton) anchorPane.lookup("#upload");

            upload.setOnAction(event -> {
                if (!(description.getText().isEmpty() && type.getText().isEmpty())){
                    Surgery surgery = new Surgery();
                    if (medicalRecordId !=0) {
                        surgery.setRecordId(medicalRecordId);
                        surgery.setType(type.getText());
                        for (MedicalRecord medicalRecord : medicalRecords) {
                            if (medicalRecord.getRecordId() == medicalRecordId)
                                surgery.setMedicalRecord(medicalRecord);
                        }
                        surgery.setSurgeryId(0);
                        surgery.setDescrption(description.getText());
                        surgery.setCreated(Date.valueOf(LocalDate.now()));
                        try {
                            Surgery surg = medicalRecordService.uploadSurgery(surgery);
                            if (surg.getRecordId() == medicalRecordId){
                                uiAlertsAndPopUp.showAlert(
                                        Alert.AlertType.CONFIRMATION,"Uploaded Successfully","Confirmation",null,null
                                ).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            uiAlertsAndPopUp.showAlert(Alert.AlertType.ERROR,e.getMessage(),"Error",null,null).show();
                        }
                    }
                }else
                    uiAlertsAndPopUp.showAlert(Alert.AlertType.INFORMATION,"No selected Medical record","Message",null, null).show();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
