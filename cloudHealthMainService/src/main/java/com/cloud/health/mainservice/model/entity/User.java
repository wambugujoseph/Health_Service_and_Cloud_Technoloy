package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Time: 12:48 AM
 * Project: cloudHealthMainService
 */
@Entity
public class User {
    private String userId;
    private String firstName;
    private String middleName;
    private String sirName;
    private String email;
    private String phoneNumber;
    private Date dob;
    private String gender;
    private String nationality;
    private String profilePhotoUrl;
    private Collection<AccessContract> accessContractsByUserId;
    private Collection<AccessLog> accessLogsByUserId;
    private Collection<Emergency> emergenciesByUserId;
    private Collection<Patient> patientsByUserId;
    private Collection<Practionner> practionnersByUserId;
    private Collection<UserProfile> userProfilesByUserId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "sir_name")
    public String getSirName() {
        return sirName;
    }

    public void setSirName(String sirName) {
        this.sirName = sirName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "profile_photo_url")
    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(sirName, user.sirName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(dob, user.dob) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(nationality, user.nationality) &&
                Objects.equals(profilePhotoUrl, user.profilePhotoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, middleName, sirName, email, phoneNumber, dob, gender, nationality, profilePhotoUrl);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<AccessContract> getAccessContractsByUserId() {
        return accessContractsByUserId;
    }

    public void setAccessContractsByUserId(Collection<AccessContract> accessContractsByUserId) {
        this.accessContractsByUserId = accessContractsByUserId;
    }

    @OneToMany(mappedBy = "userByAccessedBy")
    public Collection<AccessLog> getAccessLogsByUserId() {
        return accessLogsByUserId;
    }

    public void setAccessLogsByUserId(Collection<AccessLog> accessLogsByUserId) {
        this.accessLogsByUserId = accessLogsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Emergency> getEmergenciesByUserId() {
        return emergenciesByUserId;
    }

    public void setEmergenciesByUserId(Collection<Emergency> emergenciesByUserId) {
        this.emergenciesByUserId = emergenciesByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Patient> getPatientsByUserId() {
        return patientsByUserId;
    }

    public void setPatientsByUserId(Collection<Patient> patientsByUserId) {
        this.patientsByUserId = patientsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Practionner> getPractionnersByUserId() {
        return practionnersByUserId;
    }

    public void setPractionnersByUserId(Collection<Practionner> practionnersByUserId) {
        this.practionnersByUserId = practionnersByUserId;
    }

    @OneToMany(mappedBy = "userByOwner")
    public Collection<UserProfile> getUserProfilesByUserId() {
        return userProfilesByUserId;
    }

    public void setUserProfilesByUserId(Collection<UserProfile> userProfilesByUserId) {
        this.userProfilesByUserId = userProfilesByUserId;
    }
}
