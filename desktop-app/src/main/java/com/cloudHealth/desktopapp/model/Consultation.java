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
public class Consultation {

    private int recordId;
    private String type;
    private Date created;
    private String descrption;
    private int consultationId;
    private MedicalRecord medicalRecord;

}
