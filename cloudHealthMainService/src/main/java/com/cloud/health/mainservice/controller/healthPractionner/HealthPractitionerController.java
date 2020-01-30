package com.cloud.health.mainservice.controller.healthPractionner;

import com.cloud.health.mainservice.model.User;
import com.cloud.health.mainservice.model.UserProfile;
import com.cloud.health.mainservice.model.entity.*;
import com.cloud.health.mainservice.model.medicalRecord.*;
import com.cloud.health.mainservice.service.filestorage.ProfileFileStorageService;
import com.cloud.health.mainservice.service.repositoryService.PractitionerMedicalRecordService;
import com.cloud.health.mainservice.service.repositoryService.PractitionerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.cloud.health.mainservice.util.Constant.*;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 12/18/2019
 * Time: 4:31 AM
* Project: cloudHealthMainService
 */

@RestController
@RequestMapping(value = API_V_1)
public class HealthPractitionerController {

    @Autowired
    private ProfileFileStorageService profileFileStorageService;

    @Autowired
    private PractitionerRepositoryService practitionerRepositoryService;

    @Autowired
    private PractitionerMedicalRecordService practitionerMedicalRecordService;


    /**
     * @param file               profile picture of client
     * @param redirectAttributes to be contained in the redirect Url
     * @return ResponseEntity
     */
    @PostMapping(value = "/register/client", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> registerClient(@RequestParam("file") MultipartFile file, @ModelAttribute User user, RedirectAttributes redirectAttributes) {

        if (!(user == null)) {
            String isUploadedPicName = profileFileStorageService.store(file);
            if (isUploadedPicName != null) {
                boolean userAdded = practitionerRepositoryService.addClient(user, isUploadedPicName);
                if (userAdded) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"Created\"}");
                }
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{\"message\":\"Not Created\"}");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(OBJECT_IS_EMPTY);
    }

    /**
     * @param user to be save in Json Format
     * @return Respose status
     */
    @PostMapping(value = "/register/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerClient(@RequestBody User user) {
        if (!(user == null)) {
            boolean userAdded = practitionerRepositoryService.addClient(user, null);
            if (userAdded) {
                return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"Created\"}");
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{\"message\":\"Not Created\"}");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(OBJECT_IS_EMPTY);
    }

    @PostMapping(value = "/create/clientProfile")
    public ResponseEntity<Object> addClientProfile(@RequestBody UserProfile userProfile) {
        if (!(userProfile == null)) {
            boolean isProfileCreated = practitionerRepositoryService.addClientProfile(userProfile);
            if (isProfileCreated) {
                return ResponseEntity.status(HttpStatus.CREATED).body("{\"Profile\":\"Created\"}");
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/create/medicalRecord")
    public ResponseEntity<Object> createMedicalRecord(@RequestBody HealthRecord healthRecord) {
        if (!(healthRecord == null)) {
            try {
                MedicalRecordEntity medicalRecordEntity = practitionerMedicalRecordService.createMedicalRecord(healthRecord);
                return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @PostMapping(value = "/patient/consultation")
    public ResponseEntity<Object> addHealthConsultation(@RequestBody Consultation consultation) {
        if (consultation != null) {
            try {
                ConsultationEntity consultationEntity = practitionerMedicalRecordService.addMedicalConsultation(consultation);
                if (consultationEntity != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(consultationEntity);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(INVALID_REQUEST_OBJECT);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @PostMapping(value = "/patient/ailment")
    public ResponseEntity<Object> addHealthConsultation(@RequestBody Ailment ailment) {
        if (ailment != null) {
            try {
                AilmentEntity ailmentEntity = practitionerMedicalRecordService.addMedicalAilment(ailment);
                if (ailmentEntity != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(ailmentEntity);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(INVALID_REQUEST_OBJECT);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @PostMapping(value = "/patient/medicalFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addHealthConsultation(@ModelAttribute MedicalFile medicalFile) {
        if (medicalFile != null) {
            try {
                MedicalFileEntity medicalFileEntity = practitionerMedicalRecordService.addMedicalFile(medicalFile);
                if (medicalFileEntity != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(medicalFileEntity);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(INVALID_REQUEST_OBJECT);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @PostMapping(value = "/patient/surgery")
    public ResponseEntity<Object> addHealthConsultation(@RequestBody Surgery surgery) {
        if (surgery != null) {
            try {
                SurgeryEntity surgeryEntity = practitionerMedicalRecordService.addMedicalSurgery(surgery);
                if (surgeryEntity != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(surgeryEntity);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(INVALID_REQUEST_OBJECT);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @PostMapping(value = "/patient/prescription")
    public ResponseEntity<Object> addHealthConsultation(@RequestBody Prescription prescription) {
        if (prescription != null) {
            try {
                PrescriptionEntity prescriptionEntity = practitionerMedicalRecordService.addMedicalPrescription(prescription);
                if (prescriptionEntity != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionEntity);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(INVALID_REQUEST_OBJECT);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @RequestMapping(value = "/practitioner/accept/request/{token}/{practitionerId}/{clientId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> acceptPersonalHealthPractitioner(@PathVariable String token, @PathVariable String practitionerId,
                                                                   @PathVariable String clientId) {
        if (!(token.isEmpty() || practitionerId.isEmpty() || clientId.isEmpty())) {
            try {
                PersonalDoctorEntity personalDoctorEntity = practitionerRepositoryService.acceptClientPersonalDoctor(token, practitionerId, clientId);
                if (personalDoctorEntity != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(personalDoctorEntity);
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }
    @RequestMapping(value = "/patient/MedicalRecords/{patientID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMedicalRecord(@PathVariable String patientID){
            if (!patientID.isEmpty()){
                try {
                    List<MedicalRecordEntity> medicalRecordEntities =  practitionerMedicalRecordService.getListMedicalRecord(patientID);
                    if (medicalRecordEntities != null){
                        return ResponseEntity.status(HttpStatus.OK).body(medicalRecordEntities);
                    }
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                }
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @RequestMapping(value = "/patient/{patientID}")
    public ResponseEntity<Object> getPatient(@PathVariable String patientID){
       PatientEntity patientEntity =  practitionerRepositoryService.getPatient(patientID);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patientEntity);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
