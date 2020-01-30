package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.Patient;
import com.cloudHealth.desktopapp.model.User;
import com.cloudHealth.desktopapp.model.UserProfile;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/23/2020
 * Time: 10:13 PM
 * Project: desktop-app
 */

public interface PatientProfileService {

    boolean createProfile(User user, UserProfile userProfile);
    //boolean creatUserProfile(UserProfile userProfile);
    Patient getPatient(int patientId);
    Patient getPatient(String userIdOrEmail);

}
