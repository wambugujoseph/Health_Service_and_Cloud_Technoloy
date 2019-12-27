package com.cloud.health.mainservice.service.repositoryService;

import com.cloud.health.mainservice.model.entity.*;
import com.cloud.health.mainservice.model.medicalRecord.*;
import com.cloud.health.mainservice.repository.HealthPractitionerRepository;
import com.cloud.health.mainservice.repository.medicalRecord.*;
import com.cloud.health.mainservice.service.filestorage.MedicalFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 12:25 PM
 * Project: cloudHealthMainService
 */

@Service
public class PractitionerMedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PractitionerRepositoryService practitionerRepositoryService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private HealthPractitionerRepository healthPractitionerRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private AilmentRepository ailmentRepository;
    @Autowired
    private MedicalFileStorageService medicalFileStorageService;
    @Autowired
    private MedicalFileRepository medicalFileRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private  PrescriptionRepository prescriptionRepository;

    public MedicalRecordEntity createMedicalRecord(HealthRecord healthRecord) {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        PatientEntity patientEntity = createPatientIfNotExist(healthRecord.getClientId());
        PractitionerEntity practitionerEntity = getPractitionerEntity(healthRecord.getPractitionerId());
        medicalRecordEntity.setPatient(patientEntity);
        medicalRecordEntity.setHealthPractitioner(practitionerEntity);
        medicalRecordEntity.setDescription(healthRecord.getDescription());
        medicalRecordEntity.setPatientId(patientEntity.getPatientId());
        medicalRecordEntity.setPractitionerId(practitionerEntity.getPractitionerId());
        medicalRecordEntity.setCreated(Date.valueOf(LocalDate.now()));
        medicalRecordEntity.setHealthUnitId(practitionerEntity.getHealthUnit().getHealthUnitId());
        return medicalRecordRepository.save(medicalRecordEntity);
    }

    public ConsultationEntity addMedicalConsultation(Consultation consultation) {
        ConsultationEntity consultationEntity = new ConsultationEntity();
        consultationEntity.setMedicalRecordByRecordId(getMedicalRecord(consultation.getUserId()));
        consultationEntity.setRecordId(getMedicalRecord(consultation.getUserId()).getRecordId());
        consultationEntity.setType(consultation.getType());
        consultationEntity.setDescrption(consultation.getDescription());
        consultationEntity.setCreated(Date.valueOf(LocalDate.now()));
        consultationEntity = consultationRepository.save(consultationEntity);
        consultationEntity.setMedicalRecordByRecordId(null);
        return consultationEntity;
    }

    public AilmentEntity addMedicalAilment(Ailment ailment) {
        AilmentEntity ailmentEntity = new AilmentEntity();
        ailmentEntity.setMedicalRecordByRecordId(getMedicalRecord(ailment.getUserId()));
        ailmentEntity.setCreated(Date.valueOf(LocalDate.now()));
        ailmentEntity.setDescription(ailment.getDescription());
        ailmentEntity.setRecordId(getMedicalRecord(ailment.getUserId()).getRecordId());
        ailmentEntity.setType(ailment.getType());
        ailmentEntity.setAilmentId(0);
        ailmentEntity = ailmentRepository.save(ailmentEntity);
        ailmentEntity.setMedicalRecordByRecordId(null);

        return ailmentEntity;
    }

    public MedicalFileEntity addMedicalFile(MedicalFile medicalFile) {
        MedicalFileEntity medicalFileEntity = new MedicalFileEntity();
        String fileName = medicalFileStorageService.store(medicalFile.getFile());
        medicalFileEntity.setCreated(Date.valueOf(LocalDate.now()));
        medicalFileEntity.setDescrption(medicalFile.getDescription());
        medicalFileEntity.setFileUrl(fileName);
        medicalFileEntity.setMedicalRecordByRecordId(getMedicalRecord(medicalFile.getUserId()));
        medicalFileEntity.setRecordType(medicalFile.getRecordType());
        medicalFileEntity.setRecordId(getMedicalRecord(medicalFile.getUserId()).getRecordId());
        medicalFileEntity = medicalFileRepository.save(medicalFileEntity);
        medicalFileEntity.setMedicalRecordByRecordId(null);
        return medicalFileEntity;
    }

    public SurgeryEntity addMedicalSurgery(Surgery surgery) {
        SurgeryEntity surgeryEntity = new SurgeryEntity();
        surgeryEntity.setCreated(Date.valueOf(LocalDate.now()));
        surgeryEntity.setDescrption(surgery.getDescription());
        surgeryEntity.setMedicalRecordByRecordId(getMedicalRecord(surgery.getUserId()));
        surgeryEntity.setType(surgery.getType());
        surgeryEntity.setRecordId(getMedicalRecord(surgery.getUserId()).getRecordId());
        surgeryEntity  = surgeryRepository.save(surgeryEntity);
        surgeryEntity.setMedicalRecordByRecordId(null);

        return surgeryEntity;
    }

    public PrescriptionEntity addMedicalPrescription(Prescription prescription) {
        PrescriptionEntity prescriptionEntity = new PrescriptionEntity();
        prescriptionEntity.setCreated(Date.valueOf(LocalDate.now()));
        prescriptionEntity.setDescrption(prescription.getDescription());
        prescriptionEntity.setMedication(prescription.getMedication());
        prescriptionEntity.setMedicalRecordByRecordId(getMedicalRecord(prescription.getUserId()));
        prescriptionEntity.setRecordId(getMedicalRecord(prescription.getUserId()).getRecordId());
        prescriptionEntity = prescriptionRepository.save(prescriptionEntity);
        prescriptionEntity.setMedicalRecordByRecordId(null);

        return prescriptionEntity;
    }

    private PatientEntity createPatientIfNotExist(String userId) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setUser(userId);
        patientEntity.setUserByUser(practitionerRepositoryService.getClientInfo(userId));
        return patientRepository.save(patientEntity);
    }

    private PractitionerEntity getPractitionerEntity(String practitionerId) {
        return healthPractitionerRepository.findById(practitionerId).orElse(null);
    }

    private MedicalRecordEntity getMedicalRecord(String userId) {
        MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.findByPatientId(
                Objects.requireNonNull(patientRepository.findByUser(userId).orElse(null)).getPatientId()
        ).orElse(null);

        return medicalRecordEntity;
    }

}
