package com.cloudHealth.desktopapp.model;

import lombok.Data;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 12/22/2019
 * Time: 1:11 AM
 * Project: cloudHealthMainService
 */

@Data
public class HealthRecord {
    private String clientId;
    private String practitionerId;
    private String HealthUnitName;
    private String description;
}
