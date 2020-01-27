package com.cloudHealth.desktopapp.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 1/27/2020
 * Time: 1:04 AM
 * Project: desktop-app
 */
@Data
public class MedicalRecordList {
    private List<MedicalRecord> medicalRecords;
}
