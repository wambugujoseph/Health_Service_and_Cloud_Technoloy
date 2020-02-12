package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.*;
import com.cloudHealth.desktopapp.service.MedicalRecordServiceImpl;
import com.cloudHealth.desktopapp.service.PatientProfileServiceImpl;
import com.cloudHealth.desktopapp.util.StringFormatter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
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
import javafx.scene.layout.VBox;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

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



    @Autowired
    private MedicalRecordServiceImpl medicalRecordService;
    @Autowired
    private PatientProfileServiceImpl patientProfileService;
    @Autowired
    private HomeController homeController;

    private JFXListView listView = new JFXListView();
    private Resource consultationContentDisplay, ailmentContentDisplay, prescriptionContentDisplay, surgeryContentDisplay,medicalFileDisplay,realTimeDisplay;
    private ApplicationContext ac;
    private MedicalRecord[] medicalRecords;
    Logger logger = LoggerFactory.getLogger(FindPatientMedicalRecordController.class);

    private Collection<MedicalRecord> medicalRecordCollection = new ArrayList<>();

    FindPatientMedicalRecordController(@Value("${classpath:/templates/helperLayout/ailmentDisplay.fxml}") Resource ailmentContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/consultationDisplay.fxml}") Resource consultationContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/prescriptionDisplay.fxml}") Resource prescriptionContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/surgeryDisplay.fxml}") Resource surgeryContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/medicalFileDisplay.fxml}") Resource medicalFileDisplay,
                                       @Value("${classpath:/templates/helperLayout/realtimeRecord.fxml}") Resource realTimeDisplay,
                                       ApplicationContext ac) {
        this.consultationContentDisplay = consultationContentDisplay;
        this.ailmentContentDisplay = ailmentContentDisplay;
        this.prescriptionContentDisplay = prescriptionContentDisplay;
        this.surgeryContentDisplay = surgeryContentDisplay;
        this.medicalFileDisplay = medicalFileDisplay;
        this.realTimeDisplay = realTimeDisplay;
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

    private void chooseDifferentMedicalRecord() {
        String selectedItemText = listView.getSelectionModel().getSelectedItem().toString();
        //logger.info("The selected item text is :" + selectedItemText);
        for (MedicalRecord medicalRecord: this.medicalRecords){
            //check if the selected listItem contain setMedical id
            if (selectedItemText.contains(medicalRecord.getRecordId()+"")){
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
            dateLabel.setText(String.valueOf(consultation.getCreated().toLocalDate()));
            typeTextDisplay.setText(consultation.getType());
            descriptionTxtDisplay.setText(consultation.getDescrption());
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
            dateLabel.setText(String.valueOf(ailment.getCreated().toLocalDate()));
            typeTextDisplay.setText(ailment.getType());
            descriptionTxtDisplay.setText(ailment.getDescription());
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

}
