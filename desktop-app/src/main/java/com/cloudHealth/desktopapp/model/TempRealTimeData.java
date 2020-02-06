package com.cloudHealth.desktopapp.model;

import lombok.Data;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 2/5/2020
 * Time: 10:53 PM
 * Project: desktop-app
 */

@Data
public class TempRealTimeData {

    private int count;
    private String id;
    private  String recordType;
    private String patientId;
    private  String dateTime;
    private  String latitude;
    private  String longitude;
    private String value;
}
