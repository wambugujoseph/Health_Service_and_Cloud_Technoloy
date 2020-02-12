package com.cloud.health.mainservice.model;

import lombok.Data;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/19/2019
 * Time: 2:39 AM
 * Project: cloudHealthMainService
 */

@Data
public class User {

    private String userId;
    private String firstName;
    private String middleName;
    private String sirName;
    private String email;
    private String phoneNumber;
    private String dob;
    private String gender;
    private String nationality;

    public User() {
    }

    public User(String userId, String firstName, String middleName, String sirName, String email, String phoneNumber, String dob, String gender, String nationality) {
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.sirName = sirName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.nationality = nationality;
    }

}
