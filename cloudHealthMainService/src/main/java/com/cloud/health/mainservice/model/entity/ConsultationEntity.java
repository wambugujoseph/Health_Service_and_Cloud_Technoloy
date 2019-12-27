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
@Table(name = "consultation", schema = "smart_health_db", catalog = "")
public class ConsultationEntity {
    private int recordId;
    private String type;
    private Date created;
    private String descrption;
    private int consultationId;
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
    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Id
    @Column(name = "consultation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultationEntity that = (ConsultationEntity) o;
        return recordId == that.recordId &&
                consultationId == that.consultationId &&
                Objects.equals(type, that.type) &&
                Objects.equals(created, that.created) &&
                Objects.equals(descrption, that.descrption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, type, created, descrption, consultationId);
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
