package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 2:03 PM
 * Project: cloudHealthMainService
 */
@Entity
@Table(name = "ailment", schema = "smart_health_db")
public class AilmentEntity {
    private int recordId;
    private String type;
    private Date created;
    private String description;
    private int ailmentId;
    private MedicalRecordEntity medicalRecordByRecordId;

    @Basic
    @Column(name = "record_id", insertable = false, updatable = false)

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic
    @Column(name = "descrption")
    public String getDescription() {
        return description;
    }

    public void setDescription(String descrption) {
        this.description = descrption;
    }

    @Id
    @Column(name = "ailment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getAilmentId() {
        return ailmentId;
    }

    public void setAilmentId(int ailmentId) {
        this.ailmentId = ailmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AilmentEntity that = (AilmentEntity) o;
        return recordId == that.recordId &&
                ailmentId == that.ailmentId &&
                Objects.equals(type, that.type) &&
                Objects.equals(created, that.created) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, type, created, description, ailmentId);
    }

    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "record_id", nullable = false)
    public MedicalRecordEntity getMedicalRecordByRecordId() {
        return medicalRecordByRecordId;
    }

    public void setMedicalRecordByRecordId(MedicalRecordEntity medicalRecordByRecordId) {
        this.medicalRecordByRecordId = medicalRecordByRecordId;
    }
}
