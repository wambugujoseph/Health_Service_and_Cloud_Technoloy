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
public class Prescription {
    private int precriptionId;
    private int recordId;
    private String medication;
    private Date created;
    private String descrption;
    private MedicalRecord medicalRecord;
}
