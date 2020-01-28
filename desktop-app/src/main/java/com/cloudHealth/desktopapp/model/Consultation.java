package com.cloudHealth.desktopapp.model;

import lombok.Data;

import java.sql.Date;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 1/27/2020
 * Time: 12:01 AM
 * Project: desktop-app
 */

public class Consultation {

    private int recordId;
    private String type;
    private Date created;
    private String descrption;
    private int consultationId;
    private MedicalRecord medicalRecord;

    public Consultation() {
    }

    public Consultation(int recordId, String type, Date created, String descrption, int consultationId, MedicalRecord medicalRecord) {
        this.recordId = recordId;
        this.type = type;
        this.created = created;
        this.descrption = descrption;
        this.consultationId = consultationId;
        this.medicalRecord = medicalRecord;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
