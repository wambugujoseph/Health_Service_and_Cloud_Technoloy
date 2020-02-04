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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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



    @Autowired
    private MedicalRecordServiceImpl medicalRecordService;
    @Autowired
    private PatientProfileServiceImpl patientProfileService;
    private Resource consultationContentDisplay, ailmentContentDisplay, prescriptionContentDisplay, surgeryContentDisplay;
    private ApplicationContext ac;


    private Collection<MedicalRecord> medicalRecordCollection = new ArrayList<>();

    FindPatientMedicalRecordController(@Value("${classpath:/templates/helperLayout/ailmentDisplay.fxml}") Resource ailmentContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/consultationDisplay.fxml}") Resource consultationContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/prescriptionDisplay.fxml}") Resource prescriptionContentDisplay,
                                       @Value("${classpath:/templates/helperLayout/surgeryDisplay.fxml}") Resource surgeryContentDisplay,
                                       ApplicationContext ac) {
        this.consultationContentDisplay = consultationContentDisplay;
        this.ailmentContentDisplay = ailmentContentDisplay;
        this.prescriptionContentDisplay = prescriptionContentDisplay;
        this.surgeryContentDisplay = surgeryContentDisplay;
        this.ac = ac;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventActionEvents();
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

    private void eventActionEvents() {
        HamburgerBackArrowBasicTransition backArrow = new HamburgerBackArrowBasicTransition(medicalRecordHamburger);
        backArrow.setRate(-1);
        patientRecordDrawerOpenClose(backArrow);
        setMedicalRecordSearch();

    }

    public void setMedicalRecordSearch() {
        medicalRecordSearch.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (!medicalRecordSearch.getText().isEmpty()) {
                    String userIdOrEmail = medicalRecordSearch.getText();
                    MedicalRecord[] medicalRecords = medicalRecordService.getAllPatientMedicalRecords(userIdOrEmail);
                    populatedDrawerContent(medicalRecords);
                    for (MedicalRecord medicalRecord : medicalRecords) {
                        displayMedicalRecord(medicalRecord);
                    }
                }
            }
        });
    }

    private void populatedDrawerContent(MedicalRecord[] medicalRecords) {
        JFXListView listView = new JFXListView();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (MedicalRecord medicalRecord : medicalRecords) {
            list.add(medicalRecord.getCreated().toString()+ ", Record Id = "+medicalRecord.getRecordId() +" \n"+ medicalRecord.getDescription()+
                    " \nPractitioner Id = "+medicalRecord.getPractitionerId());
        }
        listView.setItems(list);
        listView.setVerticalGap(5.0);
        listView.setBorder(Border.EMPTY);
        listView.prefWidth(medicalRecordDrawer.getWidth());

        medicalRecordDrawer.setSidePane(listView);
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

        displayPatientDetails(medicalRecord.getPatientId());

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
        });

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


}
