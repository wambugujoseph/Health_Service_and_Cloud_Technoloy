package com.cloudHealth.desktopapp.model;

import lombok.Data;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 1/26/2020
 * Time: 11:58 PM
 * Project: desktop-app
 */

@Data
public class MedicalRecord {
    private int recordId;
    private Date created;
    private String description;
    private int patientId;
    private String practitionerId;
    private Integer healthUnitId;
    private Patient patient;
    private Practitioner healthPractitioner;
    private Collection<Prescription> prescriptions;
    private Collection<Ailment> ailments;
    private Collection<Consultation> consultations;
    private Collection<Surgery> surgeries;
    private Collection<MedicalFile> medicalFiles;

}
