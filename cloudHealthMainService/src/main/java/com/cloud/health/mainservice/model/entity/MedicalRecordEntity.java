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
    private Collection<PrescriptionEntity> prescriptionsByRecordId;
    private Collection<AilmentEntity> ailmentsByRecordId;
    private Collection<ConsultationEntity> consultationsByRecordId;
    private Collection<SurgeryEntity> surgeriesByRecordId;
    private Collection<MedicalFileEntity> medicalFilesByRecordId;


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

    @OneToMany(mappedBy = "medicalRecordByRecordId")
    public Collection<AilmentEntity> getAilmentsByRecordId() {
        return ailmentsByRecordId;
    }

    public void setAilmentsByRecordId(Collection<AilmentEntity> ailmentsByRecordId) {
        this.ailmentsByRecordId = ailmentsByRecordId;
    }

    @OneToMany(mappedBy = "medicalRecordByRecordId")
    public Collection<ConsultationEntity> getConsultationsByRecordId() {
        return consultationsByRecordId;
    }

    public void setConsultationsByRecordId(Collection<ConsultationEntity> consultationsByRecordId) {
        this.consultationsByRecordId = consultationsByRecordId;
    }

    @ManyToOne
    @JoinColumn(name = "practionner_id", referencedColumnName = "practitioner_id")
    public PractitionerEntity getHealthPractitioner() {
        return healthPractitioner;
    }

    public void setHealthPractitioner(PractitionerEntity practitionerByPractionnerId) {
        this.healthPractitioner = practitionerByPractionnerId;
    }

    @OneToMany(mappedBy = "medicalRecordByRecordId")
    public Collection<PrescriptionEntity> getPrescriptionsByRecordId() {
        return prescriptionsByRecordId;
    }

    public void setPrescriptionsByRecordId(Collection<PrescriptionEntity> prescriptionsByRecordId) {
        this.prescriptionsByRecordId = prescriptionsByRecordId;
    }

    @OneToMany(mappedBy = "medicalRecordByRecordId")
    public Collection<SurgeryEntity> getSurgeriesByRecordId() {
        return surgeriesByRecordId;
    }

    public void setSurgeriesByRecordId(Collection<SurgeryEntity> surgeriesByRecordId) {
        this.surgeriesByRecordId = surgeriesByRecordId;
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

    @OneToMany(mappedBy = "medicalRecordByRecordId")
    public Collection<MedicalFileEntity> getMedicalFilesByRecordId() {
        return medicalFilesByRecordId;
    }

    public void setMedicalFilesByRecordId(Collection<MedicalFileEntity> medicalFilesByRecordId) {
        this.medicalFilesByRecordId = medicalFilesByRecordId;
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
