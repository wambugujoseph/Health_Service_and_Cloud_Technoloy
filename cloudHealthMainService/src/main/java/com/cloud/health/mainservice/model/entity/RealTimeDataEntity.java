package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/26/2019
 * Time: 1:01 PM
 * Project: cloudHealthMainService
 */

@Entity
@Table(name = "real_time_data", schema = "smart_health_db", catalog = "")
public class RealTimeDataEntity {
    private int realTimeId;
    private int patientId;
    private String recordType;
    private Timestamp created;
    private int locationId;
    private String value;
    private PatientEntity patientByPatientId;
    private GeoLocationEntity geoLocationByLocationId;

    @Id
    @Column(name = "real_time_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getRealTimeId() {
        return realTimeId;
    }

    public void setRealTimeId(int realTimeId) {
        this.realTimeId = realTimeId;
    }

    @Basic
    @Column(name = "patient_id", insertable = false,updatable = false)
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "record_type")
    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "location_id", insertable = false,updatable = false)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealTimeDataEntity that = (RealTimeDataEntity) o;
        return realTimeId == that.realTimeId &&
                patientId == that.patientId &&
                locationId == that.locationId &&
                Objects.equals(recordType, that.recordType) &&
                Objects.equals(created, that.created) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realTimeId, patientId, recordType, created, locationId, value);
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    public PatientEntity getPatientByPatientId() {
        return patientByPatientId;
    }

    public void setPatientByPatientId(PatientEntity patientByPatientId) {
        this.patientByPatientId = patientByPatientId;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    public GeoLocationEntity getGeoLocationByLocationId() {
        return geoLocationByLocationId;
    }

    public void setGeoLocationByLocationId(GeoLocationEntity geoLocationByLocationId) {
        this.geoLocationByLocationId = geoLocationByLocationId;
    }
}
