package com.cloudHealth.desktopapp.config;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/16/2020
 * Time: 11:40 PM
 * Project: desktop-app
 */

public class Constant {
    public static final String ACCESS_TOKEN_URL ="http://localhost:9191/oauth/token";
    public static final String CLIENT_ID ="mobile";
    public static final String CLIENT_SECRET ="pin";
    public static final String ACCESS_TOKEN_FILE = "user-access-token.json";
    public static final String CHECK_TOKEN_URL = "http://localhost:9191/oauth/token";
    private static final String MAIN_SERVICE_BASE_URL ="http://localhost:8080/api/v1";
    public static final String CLIENT_REGISTER_URL = MAIN_SERVICE_BASE_URL+"/register/client";
    public static final String CLIENT_PROFILE_URL = MAIN_SERVICE_BASE_URL+"/clientProfile";
    public static final String PATIENT_RECORD_URL = MAIN_SERVICE_BASE_URL+"/patient/medicalRecord/";
    public static final String PATIENT_DETAILS = MAIN_SERVICE_BASE_URL+"/patient/";
    public static final String PRACTITIONER_DETAILS = MAIN_SERVICE_BASE_URL+"/practitioner/";
    public static final String MEDICAL_CONSULTATION = MAIN_SERVICE_BASE_URL+"/patient/consultation";
    public static final String MEDICAL_SURGERY = MAIN_SERVICE_BASE_URL+"/patient/surgery";
    public static final String MEDICAL_AILMENT = MAIN_SERVICE_BASE_URL+"/patient/ailment";
    public static final String MEDICAL_PRESCRIPTION = MAIN_SERVICE_BASE_URL+"/patient/prescription";
    public static final String MEDICAL_FILE = MAIN_SERVICE_BASE_URL+"/patient/medicalFile";
    public static final String MEDICAL_REALTIME_DATE = MAIN_SERVICE_BASE_URL+"/patient/realTimeRecord/";
}
