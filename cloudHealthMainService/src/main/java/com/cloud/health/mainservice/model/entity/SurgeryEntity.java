package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 2:04 PM
 * Project: cloudHealthMainService
 */
@Entity
@Table(name = "surgery", schema = "smart_health_db", catalog = "")
public class SurgeryEntity {
    private int surgeryId;
    private int recordId;
    private Date created;
    private String type;
    private String descrption;
    private MedicalRecordEntity medicalRecord;

    @Id
    @Column(name = "surgery_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(int surgeryId) {
        this.surgeryId = surgeryId;
    }

    @Basic
    @Column(name = "record_id", insertable = false, updatable = false)
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "descrption")
    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurgeryEntity that = (SurgeryEntity) o;
        return surgeryId == that.surgeryId &&
                recordId == that.recordId &&
                Objects.equals(created, that.created) &&
                Objects.equals(type, that.type) &&
                Objects.equals(descrption, that.descrption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surgeryId, recordId, created, type, descrption);
    }

    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "record_id", nullable = false)
    public MedicalRecordEntity getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecordEntity medicalRecordByRecordId) {
        this.medicalRecord = medicalRecordByRecordId;
    }
}
