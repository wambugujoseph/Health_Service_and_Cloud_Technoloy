package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "geo_location", schema = "smart_health_db", catalog = "")
public class GeoLocationEntity {
    private int locationId;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal error;
    private String locationDescription;

    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "longitude")
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude")
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "error")
    public BigDecimal getError() {
        return error;
    }

    public void setError(BigDecimal error) {
        this.error = error;
    }

    @Basic
    @Column(name = "location_description")
    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoLocationEntity that = (GeoLocationEntity) o;
        return locationId == that.locationId &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(error, that.error) &&
                Objects.equals(locationDescription, that.locationDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, longitude, latitude, error, locationDescription);
    }
}
