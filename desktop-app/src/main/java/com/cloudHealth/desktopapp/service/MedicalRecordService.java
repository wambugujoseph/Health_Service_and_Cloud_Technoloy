package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.MedicalRecord;
import com.cloudHealth.desktopapp.model.MedicalRecordList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 1/26/2020
 * Time: 11:56 PM
 * Project: desktop-app
 */

public interface MedicalRecordService {

     Object getAllPatientMedicalRecords(String userIdOrEmail);
}
