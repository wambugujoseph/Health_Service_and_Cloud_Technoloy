package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/26/2019
 * Time: 6:30 AM
 * Project: cloudHealthMainService
 */

@Entity
@Table(name = "personal_doctor", schema = "smart_health_db", catalog = "")
@IdClass(PersonalDoctorEntityPK.class)
public class PersonalDoctorEntity {
    private String practitionerId;
    private String doctorTo;
    private int active;
    private String token;

    @Id
    @Column(name = "practitioner_id", insertable = false, updatable = false)
    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    @Id
    @Column(name = "doctor_to", insertable = false, updatable = false)
    public String getDoctorTo() {
        return doctorTo;
    }

    public void setDoctorTo(String doctorTo) {
        this.doctorTo = doctorTo;
    }

    @Basic
    @Column(name = "active")
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDoctorEntity that = (PersonalDoctorEntity) o;
        return active == that.active &&
                Objects.equals(practitionerId, that.practitionerId) &&
                Objects.equals(doctorTo, that.doctorTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(practitionerId, doctorTo, active);
    }

}
