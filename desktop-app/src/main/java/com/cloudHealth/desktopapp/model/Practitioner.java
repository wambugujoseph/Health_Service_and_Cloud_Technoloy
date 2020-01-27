package com.cloudHealth.desktopapp.model;

import lombok.Data;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 1/27/2020
 * Time: 12:00 AM
 * Project: desktop-app
 */
@Data
public class Practitioner {
    private String practitionerId;
    private String userId;
    private String title;
    private String skill;
    private String description;
    private int healthUnitId;
    private User user;
    private HealthUnit healthUnit;
}
