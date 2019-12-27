package com.cloud.health.mainservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/26/2019
 * Time: 6:30 AM
 * Project: cloudHealthMainService
 */

public class PersonalDoctorEntityPK implements Serializable {
    private String practitionerId;
    private String doctorTo;

    @Column(name = "practitioner_id", insertable = false, updatable = false)
    @Id
    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    @Column(name = "doctor_to", insertable = false, updatable = false)
    @Id
    public String getDoctorTo() {
        return doctorTo;
    }

    public void setDoctorTo(String doctorTo) {
        this.doctorTo = doctorTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDoctorEntityPK that = (PersonalDoctorEntityPK) o;
        return Objects.equals(practitionerId, that.practitionerId) &&
                Objects.equals(doctorTo, that.doctorTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(practitionerId, doctorTo);
    }
}
