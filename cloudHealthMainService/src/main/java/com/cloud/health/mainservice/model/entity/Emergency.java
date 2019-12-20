package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Time: 12:48 AM
 * Project: cloudHealthMainService
 */
@Entity
public class Emergency {
    private int emergencyId;
    private Timestamp emergencyTime;
    private User userByUserId;

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
        Emergency emergency = (Emergency) o;
        return emergencyId == emergency.emergencyId &&
                Objects.equals(emergencyTime, emergency.emergencyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emergencyId, emergencyTime);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
