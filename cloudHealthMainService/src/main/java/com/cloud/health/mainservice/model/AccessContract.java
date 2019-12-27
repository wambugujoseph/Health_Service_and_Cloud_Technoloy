package com.cloud.health.mainservice.model;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 1:23 PM
 * Project: cloudHealthMainService
 */

public class AccessContract {
    private String relationship;
    private String accessLevel;
    private String userIdOrEmail;
    private String grantedToUserIdOrEmail;

    public AccessContract() {
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getUserIdOrEmail() {
        return userIdOrEmail;
    }

    public void setUserIdOrEmail(String userIdOrEmail) {
        this.userIdOrEmail = userIdOrEmail;
    }

    public String getGrantedToUserIdOrEmail() {
        return grantedToUserIdOrEmail;
    }

    public void setGrantedToUserIdOrEmail(String grantedToUserIdOrEmail) {
        this.grantedToUserIdOrEmail = grantedToUserIdOrEmail;
    }
}

