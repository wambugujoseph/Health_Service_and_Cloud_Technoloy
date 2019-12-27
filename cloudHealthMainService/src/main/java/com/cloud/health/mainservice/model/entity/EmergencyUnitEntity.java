package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 1:40 AM
 * Project: cloudHealthMainService
 */
@Entity
@Table(name = "emergency_unit", schema = "smart_health_db", catalog = "")
public class EmergencyUnitEntity {
    private int emergencyUnitId;
    private String name;
    private int active;
    private HealthUnitEntity healthUnitByHealthUnitId;

    @Id
    @Column(name = "emergency_unit_id")
    public int getEmergencyUnitId() {
        return emergencyUnitId;
    }

    public void setEmergencyUnitId(int emergencyUnitId) {
        this.emergencyUnitId = emergencyUnitId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "active")
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmergencyUnitEntity that = (EmergencyUnitEntity) o;
        return emergencyUnitId == that.emergencyUnitId &&
                active == that.active &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emergencyUnitId, name, active);
    }

    @ManyToOne
    @JoinColumn(name = "health_unit_id", referencedColumnName = "health_unit_id", nullable = false)
    public HealthUnitEntity getHealthUnitByHealthUnitId() {
        return healthUnitByHealthUnitId;
    }

    public void setHealthUnitByHealthUnitId(HealthUnitEntity healthUnitByHealthUnitId) {
        this.healthUnitByHealthUnitId = healthUnitByHealthUnitId;
    }
}
