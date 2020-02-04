package com.cloudHealth.desktopapp.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 2/1/2020
 * Time: 9:52 PM
 * Project: desktop-app
 */

@Data
public class UploadMedicalFile {
    private String userId;
    private String recordType;
    private String description;
    private MultipartFile file;
    private String medicalRecordId;
}
