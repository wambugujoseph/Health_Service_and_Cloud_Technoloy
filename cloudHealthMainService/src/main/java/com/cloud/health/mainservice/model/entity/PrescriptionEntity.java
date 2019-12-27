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
@Table(name = "prescription", schema = "smart_health_db", catalog = "")
public class PrescriptionEntity {
    private int precriptionId;
    private int recordId;
    private String medication;
    private Date created;
    private String descrption;
    private MedicalRecordEntity medicalRecordByRecordId;

    @Id
    @Column(name = "precription_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getPrecriptionId() {
        return precriptionId;
    }

    public void setPrecriptionId(int precriptionId) {
        this.precriptionId = precriptionId;
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
    @Column(name = "medication")
    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
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
        PrescriptionEntity that = (PrescriptionEntity) o;
        return precriptionId == that.precriptionId &&
                recordId == that.recordId &&
                Objects.equals(medication, that.medication) &&
                Objects.equals(created, that.created) &&
                Objects.equals(descrption, that.descrption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(precriptionId, recordId, medication, created, descrption);
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
