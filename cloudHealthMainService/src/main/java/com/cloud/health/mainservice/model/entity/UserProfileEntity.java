package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 1:36 AM
 * Project: cloudHealthMainService
 */
@Entity
@Table(name = "user_profile", schema = "smart_health_db", catalog = "")
public class UserProfileEntity {
    private int profileId;
    private String bloodGroup;
    private String specialNeeds;
    private String complication;
    private String insuranceInformation;
    private UserEntity owner;

    @Id
    @Column(name = "profile_id")
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "blood_group")
    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Basic
    @Column(name = "special_needs")
    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    @Basic
    @Column(name = "complication")
    public String getComplication() {
        return complication;
    }

    public void setComplication(String complication) {
        this.complication = complication;
    }

    @Basic
    @Column(name = "insurance_information")
    public String getInsuranceInformation() {
        return insuranceInformation;
    }

    public void setInsuranceInformation(String insuranceInformation) {
        this.insuranceInformation = insuranceInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileEntity that = (UserProfileEntity) o;
        return profileId == that.profileId &&
                Objects.equals(bloodGroup, that.bloodGroup) &&
                Objects.equals(specialNeeds, that.specialNeeds) &&
                Objects.equals(complication, that.complication) &&
                Objects.equals(insuranceInformation, that.insuranceInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, bloodGroup, specialNeeds, complication, insuranceInformation);
    }

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "user_id", nullable = false)
    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity userByOwner) {
        this.owner = userByOwner;
    }

}
