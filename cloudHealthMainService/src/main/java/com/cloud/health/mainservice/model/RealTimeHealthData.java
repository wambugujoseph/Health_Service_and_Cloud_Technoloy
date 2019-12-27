package com.cloud.health.mainservice.model;

import com.cloud.health.mainservice.model.entity.GeoLocationEntity;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/26/2019
 * Time: 1:16 PM
 * Project: cloudHealthMainService
 */

public class RealTimeHealthData {

    private String patientUserIdOrEmail;
    private String recordType;
    private GeoLocationEntity geoLocation;
    private String value;

    public RealTimeHealthData() {
    }

    public String getPatientUserIdOrEmail() {
        return patientUserIdOrEmail;
    }

    public void setPatientUserIdOrEmail(String patientUserIdOrEmail) {
        this.patientUserIdOrEmail = patientUserIdOrEmail;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public GeoLocationEntity getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocationEntity geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
