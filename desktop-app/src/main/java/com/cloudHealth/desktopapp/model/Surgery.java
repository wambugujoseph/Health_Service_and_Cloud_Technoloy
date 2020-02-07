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

@Data
public class Surgery {
    private int surgeryId;
    private int recordId;
    private Date created;
    private String type;
    private String descrption;
    private MedicalRecord medicalRecord;
}
