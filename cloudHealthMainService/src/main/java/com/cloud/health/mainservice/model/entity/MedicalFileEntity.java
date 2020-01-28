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
@Table(name = "medical_file", schema = "smart_health_db")
public class MedicalFileEntity {
    private int recordId;
    private String fileUrl;
    private String recordType;
    private Date created;
    private String descrption;
    private int medicalFileId;
    private MedicalRecordEntity medicalRecord;

    @Basic
    @Column(name = "record_id", insertable = false, updatable = false)
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "file_url")
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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
    @Column(name = "madical_file_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getMedicalFileId() {
        return medicalFileId;
    }

    public void setMedicalFileId(int madicalFileId) {
        this.medicalFileId = madicalFileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalFileEntity that = (MedicalFileEntity) o;
        return recordId == that.recordId &&
                medicalFileId == that.medicalFileId &&
                Objects.equals(fileUrl, that.fileUrl) &&
                Objects.equals(recordType, that.recordType) &&
                Objects.equals(created, that.created) &&
                Objects.equals(descrption, that.descrption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, fileUrl, recordType, created, descrption, medicalFileId);
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
