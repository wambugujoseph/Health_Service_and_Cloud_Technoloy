package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
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
@Table(name = "patient", schema = "smart_health_db", catalog = "")
public class PatientEntity {
    private int patientId;
    private String user;
    private UserEntity userByUser;

    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return patientId == that.patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }

    @Basic
    @Column(name = "user", insertable = false, updatable = false)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUser() {
        return userByUser;
    }

    public void setUserByUser(UserEntity userByUser) {
        this.userByUser = userByUser;
    }
}
