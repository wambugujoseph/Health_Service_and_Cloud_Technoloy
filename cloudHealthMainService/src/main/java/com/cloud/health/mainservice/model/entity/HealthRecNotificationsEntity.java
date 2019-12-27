package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 2:44 PM
 * Project: cloudHealthMainService
 */

@Entity
@Table(name = "health_rec_notifications", schema = "smart_health_db", catalog = "")
public class HealthRecNotificationsEntity {
    private int notificationId;
    private String notificationFrom;
    private String message;
    private Integer accepted;
    private String acceptUrl;
    private String notificationTo;
    private UserEntity userByNotificationFrom;
    private UserEntity userByNotificationTo;

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @Basic
    @Column(name = "notification_from", insertable = false, updatable = false)
    public String getNotificationFrom() {
        return notificationFrom;
    }

    public void setNotificationFrom(String notificationFrom) {
        this.notificationFrom = notificationFrom;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "accepted")
    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    @Basic
    @Column(name = "accept_url")
    public String getAcceptUrl() {
        return acceptUrl;
    }

    public void setAcceptUrl(String acceptUrl) {
        this.acceptUrl = acceptUrl;
    }

    @Basic
    @Column(name = "notification_to", insertable = false, updatable = false)
    public String getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(String notificationTo) {
        this.notificationTo = notificationTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthRecNotificationsEntity that = (HealthRecNotificationsEntity) o;
        return notificationId == that.notificationId &&
                Objects.equals(notificationFrom, that.notificationFrom) &&
                Objects.equals(message, that.message) &&
                Objects.equals(accepted, that.accepted) &&
                Objects.equals(acceptUrl, that.acceptUrl) &&
                Objects.equals(notificationTo, that.notificationTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, notificationFrom, message, accepted, acceptUrl, notificationTo);
    }

    @ManyToOne
    @JoinColumn(name = "notification_from", referencedColumnName = "user_id")
    public UserEntity getUserByNotificationFrom() {
        return userByNotificationFrom;
    }

    public void setUserByNotificationFrom(UserEntity userByNotificationFrom) {
        this.userByNotificationFrom = userByNotificationFrom;
    }

    @ManyToOne
    @JoinColumn(name = "notification_to", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByNotificationTo() {
        return userByNotificationTo;
    }

    public void setUserByNotificationTo(UserEntity userByNotificationTo) {
        this.userByNotificationTo = userByNotificationTo;
    }
}
