package com.cloudHealth.desktopapp.model;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Time: 3:35 AM
 * Project: cloudHealthMainService
 */
public class Profile {

    private int profileId;
    private String bloodGroup;
    private String specialNeeds;
    private String complication;
    private String insuranceInformation;
    private String ownerId;

    public Profile(int profileId, String bloodGroup, String specialNeeds, String complication, String insuranceInformation, String ownerId) {
        this.profileId = profileId;
        this.bloodGroup = bloodGroup;
        this.specialNeeds = specialNeeds;
        this.complication = complication;
        this.insuranceInformation = insuranceInformation;
        this.ownerId = ownerId;
    }

    public Profile() {
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public String getComplication() {
        return complication;
    }

    public void setComplication(String complication) {
        this.complication = complication;
    }

    public String getInsuranceInformation() {
        return insuranceInformation;
    }

    public void setInsuranceInformation(String insuranceInformation) {
        this.insuranceInformation = insuranceInformation;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
