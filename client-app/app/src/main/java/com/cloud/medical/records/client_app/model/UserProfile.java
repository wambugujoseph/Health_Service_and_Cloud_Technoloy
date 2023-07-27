package com.cloud.medical.records.client_app.model;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Time: 3:35 AM
 * Project: cloudHealthMainService
 */
public class UserProfile {

    private int profileId;
    private String bloodGroup;
    private String specialNeeds;
    private String complication;
    private String insuranceInformation;
    private User owner;

    public UserProfile() {
    }
    public UserProfile(UserProfile userProfile) {
        this.profileId = userProfile.getProfileId();
        this.bloodGroup = userProfile.getBloodGroup();
        this.specialNeeds = userProfile.getSpecialNeeds();
        this.complication = userProfile.getComplication();
        this.insuranceInformation = userProfile.getInsuranceInformation();
        this.owner = userProfile.getOwner();
    }

    public UserProfile(int profileId, String bloodGroup, String specialNeeds, String complication, String insuranceInformation, User ownerId) {
        this.profileId = profileId;
        this.bloodGroup = bloodGroup;
        this.specialNeeds = specialNeeds;
        this.complication = complication;
        this.insuranceInformation = insuranceInformation;
        this.owner = ownerId;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
