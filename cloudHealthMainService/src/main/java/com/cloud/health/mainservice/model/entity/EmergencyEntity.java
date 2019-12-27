package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 1:36 AM
 * Project: cloudHealthMainService
 */
@Entity
@Table(name = "emergency", schema = "smart_health_db", catalog = "")
public class EmergencyEntity {
    private int emergencyId;
    private Timestamp emergencyTime;
    private UserEntity userByClient;

    @Id
    @Column(name = "emergency_id")
    public int getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(int emergencyId) {
        this.emergencyId = emergencyId;
    }

    @Basic
    @Column(name = "emergency_time")
    public Timestamp getEmergencyTime() {
        return emergencyTime;
    }

    public void setEmergencyTime(Timestamp emergencyTime) {
        this.emergencyTime = emergencyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmergencyEntity that = (EmergencyEntity) o;
        return emergencyId == that.emergencyId &&
                Objects.equals(emergencyTime, that.emergencyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emergencyId, emergencyTime);
    }

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByClient() {
        return userByClient;
    }

    public void setUserByClient(UserEntity userByClient) {
        this.userByClient = userByClient;
    }

}
