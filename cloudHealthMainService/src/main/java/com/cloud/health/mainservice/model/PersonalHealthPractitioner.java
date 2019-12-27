package com.cloud.health.mainservice.model;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Tuesday
 * Date: 12/24/2019
 * Time: 10:26 AM
 * Project: cloudHealthMainService
 */

public class PersonalHealthPractitioner {

    private String practitionerUserIdOrEmail;
    private String clientIdOrEmail;

    public PersonalHealthPractitioner() {
    }

    public String getPractitionerUserIdOrEmail() {
        return practitionerUserIdOrEmail;
    }

    public void setPractitionerUserIdOrEmail(String practitionerUserIdOrEmail) {
        this.practitionerUserIdOrEmail = practitionerUserIdOrEmail;
    }

    public String getClientIdOrEmail() {
        return clientIdOrEmail;
    }

    public void setClientIdOrEmail(String clientIdOrEmail) {
        this.clientIdOrEmail = clientIdOrEmail;
    }
}
