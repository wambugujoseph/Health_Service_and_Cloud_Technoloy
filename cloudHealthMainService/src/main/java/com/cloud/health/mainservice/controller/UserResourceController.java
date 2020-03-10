package com.cloud.health.mainservice.controller;

import com.cloud.health.mainservice.model.AccessContract;
import com.cloud.health.mainservice.model.PersonalHealthPractitioner;
import com.cloud.health.mainservice.model.RealTimeHealthData;
import com.cloud.health.mainservice.model.entity.AccessContractEntity;
import com.cloud.health.mainservice.model.entity.PersonalDoctorEntity;
import com.cloud.health.mainservice.model.entity.RealTimeDataEntity;
import com.cloud.health.mainservice.model.entity.UserEntity;
import com.cloud.health.mainservice.repository.HealthRecNotificationRepository;
import com.cloud.health.mainservice.service.repositoryService.ClientService;
import com.cloud.health.mainservice.util.auth.UserAuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cloud.health.mainservice.util.Constant.*;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 12:57 PM
 * Project: cloudHealthMainService
 */

@RestController
@RequestMapping(value = API_V_1)
public class UserResourceController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserAuthenticationDetails userAuthenticationDetails;

    @RequestMapping(value = "/username")
    public String getUserName() {
        return "++++--->>" + userAuthenticationDetails.getUserEmail().getEmail();
    }

    @GetMapping(value = "/userInfo/{id}")
    public UserEntity getUserDetail(@PathVariable String id) {
        return clientService.getUserInfo(id);
    }

    @PostMapping(value = "client/accessContract")
    public ResponseEntity<Object> createAccessSContract(@RequestBody AccessContract accessContract) {
        if (accessContract != null) {
            try {
                AccessContractEntity accessContractEntity = clientService.addAccessContract(accessContract);
                if (accessContractEntity != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(accessContractEntity);
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);

            } catch (Exception e) {
                //e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @GetMapping(value = "client/accessContract")
    public ResponseEntity<Object> getAccessContract(){
        try {
            List<AccessContractEntity> userEntities = clientService.getAccessContractEntities();
            return ResponseEntity.ok().body(userEntities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "client/accessContract/{accessEmailOrId}")
    public ResponseEntity<Object> deleteAccessContract(@PathVariable String accessEmailOrId){
        try {
            boolean isDeleted = clientService.deleteAccessGrant(accessEmailOrId);
            if (isDeleted) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "client/accessContract/block/{accessEmailOrId}/{state}")
    public ResponseEntity<Object> blockAccessContract(@PathVariable String accessEmailOrId, @PathVariable String state){
        try {
            int activate = Integer.parseInt(state);
            AccessContractEntity accessContractEntity =  clientService.blockAccessGrant(accessEmailOrId, activate);
            return ResponseEntity.status(HttpStatus.OK).body(accessContractEntity);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/client/accept/accessContract/{token}/{contractId}")
    public ResponseEntity<Object> acceptAccessContract(@PathVariable String token, @PathVariable Integer contractId) {
        AccessContractEntity accessContractEntity = clientService.acceptMedicalRecordAccessContract(token, contractId);
        if (!(accessContractEntity == null))
            return ResponseEntity.status(HttpStatus.CREATED).body(accessContractEntity);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INVALID_REQUEST_OBJECT);
    }

    @PostMapping(value = "client/personalHealthPractitioner")
    public ResponseEntity<Object> setPersonalHealthPractitioner(@RequestBody PersonalHealthPractitioner personalHealthPractitioner) {

        if (personalHealthPractitioner != null) {
            PersonalDoctorEntity doctorEntity = clientService.addPersonalHealthPractitioner(personalHealthPractitioner);
            return ResponseEntity.status(HttpStatus.CREATED).body(doctorEntity);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(COULD_NOT_PROCESS);
    }

    @PostMapping(value = "/client/real-time")
    public ResponseEntity<Object> addRealTimeHealthData(@RequestBody RealTimeHealthData realTimeHealthData) {
        if (realTimeHealthData != null) {
            RealTimeDataEntity realTimeDataEntity;
            try {
                realTimeDataEntity = clientService.addRealTimeHealthData(realTimeHealthData);
                return ResponseEntity.status(HttpStatus.CREATED).body(realTimeDataEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
            }
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_REQUEST_OBJECT);
    }

    @GetMapping(value = "/client/notifications")
    public ResponseEntity<Object> getClientNotifications(){

        return  ResponseEntity.ok().body(clientService.getAllNotifications());
    }


}
