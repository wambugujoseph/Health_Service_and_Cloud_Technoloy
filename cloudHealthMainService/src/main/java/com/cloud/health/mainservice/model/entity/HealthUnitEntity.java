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
@Table(name = "health_unit", schema = "smart_health_db", catalog = "")
public class HealthUnitEntity {
    private int healthUnitId;
    private int location;
    private String description;
    private String healthUnitName;
    private GeoLocationEntity geoLocation;

    @Id
    @Column(name = "health_unit_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getHealthUnitId() {
        return healthUnitId;
    }

    public void setHealthUnitId(int healthUnitId) {
        this.healthUnitId = healthUnitId;
    }

    @Basic
    @Column(name = "location", insertable=false, updatable= false)
    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "health_unit_name")
    public String getHealthUnitName() {
        return healthUnitName;
    }

    public void setHealthUnitName(String healthUnitName) {
        this.healthUnitName = healthUnitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthUnitEntity that = (HealthUnitEntity) o;
        return healthUnitId == that.healthUnitId &&
                location == that.location &&
                Objects.equals(description, that.description) &&
                Objects.equals(healthUnitName, that.healthUnitName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthUnitId, location, description, healthUnitName);
    }

    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "location_id", nullable = false)
    public GeoLocationEntity getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocationEntity geoLocationByLocation) {
        this.geoLocation = geoLocationByLocation;
    }

}
