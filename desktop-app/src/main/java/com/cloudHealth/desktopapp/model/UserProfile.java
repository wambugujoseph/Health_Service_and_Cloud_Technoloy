package com.cloudHealth.desktopapp.model;

import lombok.Data;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Time: 3:35 AM
 * Project: cloudHealthMainService
 */
@Data
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


}
