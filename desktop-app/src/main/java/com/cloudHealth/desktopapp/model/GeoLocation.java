package com.cloudHealth.desktopapp.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 1/27/2020
 * Time: 12:11 AM
 * Project: desktop-app
 */

@Data
public class GeoLocation {
    private int locationId;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal error;
    private String locationDescription;
}
