package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.*;
import org.springframework.util.MultiValueMap;

import java.util.List;


/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 1/26/2020
 * Time: 11:56 PM
 * Project: desktop-app
 */

public interface MedicalRecordService {

     MedicalRecord[] getAllPatientMedicalRecords(String userIdOrEmail);
     Practitioner getPractitioner(String userIdOrEmail);
     MedicalRecord createMedicalRecord(HealthRecord healthRecord);
     Consultation uploadConsultation(Consultation consultation);
     Surgery uploadSurgery(Surgery surgery);
     Ailment uploadAilment(Ailment ailment);
     Prescription uploadPrescriptionAndMedication(Prescription prescription);
     MedicalFile uploadMedicalFile(MultiValueMap<String, Object> body);
     RealTimeData[] getRealTimeRecord(String patientId);
}
