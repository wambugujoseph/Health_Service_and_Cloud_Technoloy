package com.cloud.health.mainservice.model.medicalRecord;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 12/22/2019
 * Time: 1:11 AM
 * Project: cloudHealthMainService
 */

public class HealthRecord {
    private String clientId;
    private String practitionerId;
    private String HealthUnitName;
    private String description;

    public HealthRecord() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getHealthUnitName() {
        return HealthUnitName;
    }

    public void setHealthUnitName(String healthUnitName) {
        HealthUnitName = healthUnitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
