package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.model.MedicalRecord;
import com.cloudHealth.desktopapp.service.MedicalRecordServiceImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private  MedicalRecordServiceImpl medicalRecordService;


    private Collection<MedicalRecord> medicalRecordCollection = new ArrayList<>();
    FindPatientMedicalRecordController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventActionEvents();
    }

    private void patientRecordDrawerOpenClose(HamburgerBackArrowBasicTransition backArrow ){

        medicalRecordHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            backArrow.setRate(backArrow.getRate()*-1);
            backArrow.play();
            if(medicalRecordDrawer.isShown())
                medicalRecordDrawer.close();
            else
                medicalRecordDrawer.open();
        });
    }

    private  void eventActionEvents(){
        HamburgerBackArrowBasicTransition backArrow  = new HamburgerBackArrowBasicTransition(medicalRecordHamburger);
        backArrow.setRate(-1);
        patientRecordDrawerOpenClose(backArrow);
        setMedicalRecordSearch();

    }

    public void setMedicalRecordSearch(){
        medicalRecordSearch.setOnKeyPressed(event ->{
            if (event.getCode().equals(KeyCode.ENTER)){
                if (!medicalRecordSearch.getText().isEmpty()){
                    String userIdOrEmail = medicalRecordSearch.getText();
                    medicalRecordService.getAllPatientMedicalRecords(userIdOrEmail);
                }
            }
        });
    }
}
