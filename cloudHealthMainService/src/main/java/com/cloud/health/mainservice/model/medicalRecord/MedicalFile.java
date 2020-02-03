package com.cloud.health.mainservice.model.medicalRecord;

import com.cloud.health.mainservice.model.entity.MedicalRecordEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 6:30 AM
 * Project: cloudHealthMainService
 */

public class MedicalFile {

    private String userId;
    private String recordType;
    private String description;
    private MultipartFile file;
    private MedicalRecordEntity medicalRecord;

    public MedicalRecordEntity getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecordEntity medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public MedicalFile() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
