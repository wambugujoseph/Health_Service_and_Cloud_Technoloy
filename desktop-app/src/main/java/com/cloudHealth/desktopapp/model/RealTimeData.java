package com.cloudHealth.desktopapp.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 2/5/2020
 * Time: 10:32 PM
 * Project: desktop-app
 */

@Data
public class RealTimeData {
    private int realTimeId;
    private int patientId;
    private String recordType;
    private Timestamp created;
    private int locationId;
    private String value;
    private Patient patientByPatientId;
    private GeoLocation geoLocationByLocationId;
}
