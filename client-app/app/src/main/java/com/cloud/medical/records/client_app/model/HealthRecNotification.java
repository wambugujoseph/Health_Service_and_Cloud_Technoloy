package com.cloud.medical.records.client_app.model;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 2:44 PM
 * Project: cloudHealthMainService
 */


public class HealthRecNotification {
    private int notificationId;
    private String notificationFrom;
    private String message;
    private Integer accepted;
    private String acceptUrl;
    private String notificationTo;
    private User userByNotificationFrom;
    private User userByNotificationTo;


    public HealthRecNotification() {
    }

    public HealthRecNotification(int notificationId, String notificationFrom, String message, Integer accepted, String acceptUrl, String notificationTo, User userByNotificationFrom, User userByNotificationTo) {
        this.notificationId = notificationId;
        this.notificationFrom = notificationFrom;
        this.message = message;
        this.accepted = accepted;
        this.acceptUrl = acceptUrl;
        this.notificationTo = notificationTo;
        this.userByNotificationFrom = userByNotificationFrom;
        this.userByNotificationTo = userByNotificationTo;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationFrom() {
        return notificationFrom;
    }

    public void setNotificationFrom(String notificationFrom) {
        this.notificationFrom = notificationFrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public String getAcceptUrl() {
        return acceptUrl;
    }

    public void setAcceptUrl(String acceptUrl) {
        this.acceptUrl = acceptUrl;
    }

    public String getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(String notificationTo) {
        this.notificationTo = notificationTo;
    }

    public User getUserByNotificationFrom() {
        return userByNotificationFrom;
    }

    public void setUserByNotificationFrom(User userByNotificationFrom) {
        this.userByNotificationFrom = userByNotificationFrom;
    }

    public User getUserByNotificationTo() {
        return userByNotificationTo;
    }

    public void setUserByNotificationTo(User userByNotificationTo) {
        this.userByNotificationTo = userByNotificationTo;
    }
}
