package com.cloud.health.mainservice.model;

import com.cloud.health.mainservice.model.entity.GeoLocationEntity;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Time: 11:46 PM
 * Project: cloudHealthMainService
 */
public class HealthUnit {
    private int healthUnitId;
    private String description;
    private String healthUnitName;
    private GeoLocationEntity geoLocation;

    public HealthUnit() {
    }

    public int getHealthUnitId() {
        return healthUnitId;
    }

    public void setHealthUnitId(int healthUnitId) {
        this.healthUnitId = healthUnitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoLocationEntity getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocationEntity geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getHealthUnitName() {
        return healthUnitName;
    }

    public void setHealthUnitName(String healthUnitName) {
        this.healthUnitName = healthUnitName;
    }

    public String toString(HealthUnit healthUnit) {
        return healthUnit.getDescription() +"  "+healthUnit.getHealthUnitName()+"  ";//geoLocation.toString();
    }
}
