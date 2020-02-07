package com.cloudHealth.desktopapp.model;

import lombok.Data;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 1/27/2020
 * Time: 12:11 AM
 * Project: desktop-app
 */

@Data
public class HealthUnit {

    private int healthUnitId;
    private int location;
    private String description;
    private String healthUnitName;
    private GeoLocation geoLocation;
}
