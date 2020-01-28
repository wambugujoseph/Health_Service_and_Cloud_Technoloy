package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.Consultation;
import com.cloudHealth.desktopapp.model.MedicalRecord;
import com.cloudHealth.desktopapp.service.MedicalRecordServiceImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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



    @Autowired
    private MedicalRecordServiceImpl medicalRecordService;
    private Resource consultationContentDisplay;
    private ApplicationContext ac;


    private Collection<MedicalRecord> medicalRecordCollection = new ArrayList<>();

    FindPatientMedicalRecordController(@Value("${classpath:/templates/helperLayout/display.fxml}") Resource consultationContentDisplay,
                                       ApplicationContext ac) {
        this.consultationContentDisplay = consultationContentDisplay;
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
                    for (MedicalRecord medicalRecord : medicalRecords) {
                        populatedDrawerContent(medicalRecord);
                        displayMedicalRecord(medicalRecord);
                        Collection<Consultation> consultations = medicalRecord.getConsultations();
                    }
                }
            }
        });
    }

    private void populatedDrawerContent(MedicalRecord medicalRecord) {
        JFXListView listView = new JFXListView();
        ObservableList<String> list = FXCollections.observableArrayList(medicalRecord.getCreated().toLocalDate().toString());
        listView.setItems(list);
        listView.setVerticalGap(5.0);
        listView.setBorder(Border.EMPTY);
        listView.prefWidth(medicalRecordDrawer.getWidth());
        medicalRecordDrawer.setSidePane(listView);
    }

    private void displayMedicalRecord(MedicalRecord medicalRecord){
        //get display Areas
        Collection<Consultation> consultations = medicalRecord.getConsultations();
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

    private void displayPatientDetails(String patientId){

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

}
