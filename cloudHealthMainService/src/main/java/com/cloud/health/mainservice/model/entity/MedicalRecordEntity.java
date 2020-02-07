package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 12:19 PM
 * Project: cloudHealthMainService
 */

@Entity
@Table(name = "medical_record", schema = "smart_health_db", catalog = "")
public class MedicalRecordEntity {
    private int recordId;
    private Date created;
    private String description;
    private int patientId;
    private String practitionerId;
    private Integer healthUnitId;
    private PatientEntity patient;
    private PractitionerEntity healthPractitioner;
    private Collection<PrescriptionEntity> prescriptions;
    private Collection<AilmentEntity> ailments;
    private Collection<ConsultationEntity> consultations;
    private Collection<SurgeryEntity> surgeries;
    private Collection<MedicalFileEntity> medicalFiles;


    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "descrption")
    public String getDescription() {
        return description;
    }

    public void setDescription(String descrption) {
        this.description = descrption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecordEntity that = (MedicalRecordEntity) o;
        return recordId == that.recordId &&
                Objects.equals(created, that.created) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, created, description);
    }

    @OneToMany(mappedBy = "medicalRecord")
    public Collection<AilmentEntity> getAilments() {
        return ailments;
    }

    public void setAilments(Collection<AilmentEntity> ailmentsByRecordId) {
        this.ailments = ailmentsByRecordId;
    }

    @OneToMany(mappedBy = "medicalRecord")
    public Collection<ConsultationEntity> getConsultations() {
        return consultations;
    }

    public void setConsultations(Collection<ConsultationEntity> consultationsByRecordId) {
        this.consultations = consultationsByRecordId;
    }

    @ManyToOne
    @JoinColumn(name = "practionner_id", referencedColumnName = "practitioner_id")
    public PractitionerEntity getHealthPractitioner() {
        return healthPractitioner;
    }

    public void setHealthPractitioner(PractitionerEntity practitionerByPractionnerId) {
        this.healthPractitioner = practitionerByPractionnerId;
    }

    @OneToMany(mappedBy = "medicalRecord")
    public Collection<PrescriptionEntity> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Collection<PrescriptionEntity> prescriptionsByRecordId) {
        this.prescriptions = prescriptionsByRecordId;
    }

    @OneToMany(mappedBy = "medicalRecord")
    public Collection<SurgeryEntity> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(Collection<SurgeryEntity> surgeriesByRecordId) {
        this.surgeries = surgeriesByRecordId;
    }

    @Basic
    @Column(name = "patient_id", insertable = false, updatable = false)
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "practionner_id", insertable = false, updatable = false)
    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practionnerId) {
        this.practitionerId = practionnerId;
    }

    @Basic
    @Column(name = "health_unit_id", insertable = false, updatable = false)
    public Integer getHealthUnitId() {
        return healthUnitId;
    }

    public void setHealthUnitId(Integer healthUnitId) {
        this.healthUnitId = healthUnitId;
    }

    @OneToMany(mappedBy = "medicalRecord")
    public Collection<MedicalFileEntity> getMedicalFiles() {
        return medicalFiles;
    }

    public void setMedicalFiles(Collection<MedicalFileEntity> medicalFilesByRecordId) {
        this.medicalFiles = medicalFilesByRecordId;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patientByPatientId) {
        this.patient = patientByPatientId;
    }
}
