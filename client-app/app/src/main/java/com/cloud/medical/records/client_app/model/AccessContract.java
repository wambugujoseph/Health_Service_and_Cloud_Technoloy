package com.cloud.medical.records.client_app.model;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 1:36 AM
 * Project: cloudHealthMainService
 */

public class AccessContract {
    private int contractId;
    private String relationship;
    private String accessLevel;
    private String grantedTo;
    private String userId;
    private int active;
    private String token;
    private User userByUserId;
    private User userByGrantedTo;


    public AccessContract() {
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
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

    public String getGrantedTo() {
        return grantedTo;
    }

    public void setGrantedTo(String grantedTo) {
        this.grantedTo = grantedTo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public User getUserByGrantedTo() {
        return userByGrantedTo;
    }

    public void setUserByGrantedTo(User userByGrantedTo) {
        this.userByGrantedTo = userByGrantedTo;
    }
}
