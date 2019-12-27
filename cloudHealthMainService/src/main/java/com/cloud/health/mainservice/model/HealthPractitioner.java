package com.cloud.health.mainservice.model;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 6:15 AM
 * Project: cloudHealthMainService
 */

public class HealthPractitioner {

    private String practitionerId;
    private String title;
    private String skill;
    private String description;
    private String healthOrgName;
    private String userId;

    public HealthPractitioner() {
    }

    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHealthOrgName() {
        return healthOrgName;
    }

    public void setHealthOrgName(String healthOrgName) {
        this.healthOrgName = healthOrgName;
    }
}
