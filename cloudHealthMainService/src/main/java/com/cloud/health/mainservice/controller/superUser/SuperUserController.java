package com.cloud.health.mainservice.controller.superUser;

import com.cloud.health.mainservice.model.HealthPractitioner;
import com.cloud.health.mainservice.model.HealthUnit;
import com.cloud.health.mainservice.model.entity.HealthUnitEntity;
import com.cloud.health.mainservice.model.entity.PractitionerEntity;
import com.cloud.health.mainservice.service.repositoryService.SuperUserRepositoryService;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.cloud.health.mainservice.util.Constant.*;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/20/2019
 * Time: 7:39 AM
 * Project: cloudHealthMainService
 */

@RestController
@RequestMapping(value = API_V_1)
public class SuperUserController {

    @Autowired
    private SuperUserRepositoryService superUserRepositoryService;

    private Logger logger = LoggerFactory.getLogger(SuperUserController.class);



    @PostMapping(value = "/healthUnit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerHealthUnit(@RequestBody HealthUnit healthUnit){
        if (!(healthUnit == null)) {

            try {
                HealthUnitEntity healthUnitEntity = superUserRepositoryService.addHealthUnit(healthUnit);
                if (healthUnitEntity == null){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INVALID_REQUEST_OBJECT);
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(healthUnitEntity);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(OBJECT_IS_EMPTY);
    }

    @GetMapping(value="healthUnit/{healthUnitId}")
    public ResponseEntity<Object> getHealthUnit(@PathVariable String healthUnitId){
        return ResponseEntity.status(HttpStatus.OK).body(superUserRepositoryService.geTHealthUnit(healthUnitId));
    }

    @PostMapping(value = "register/practitioner",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerHealthPractitioner(@RequestBody HealthPractitioner healthPractitioner){
        if (!(healthPractitioner == null)){
            try{
                PractitionerEntity practitionerEntity = superUserRepositoryService.addPractitioner(healthPractitioner);
                return ResponseEntity.status(HttpStatus.CREATED).body(practitionerEntity);
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(OBJECT_IS_EMPTY);
    }

    @PostMapping(value = "register/practitioner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> registerHealthPractitioner(@RequestParam("user") String user, @RequestParam("practitioner")String practitioner, @RequestParam("file") MultipartFile file){

        if (user != null && practitioner != null && file.getSize() > 0){
            try {
                return superUserRepositoryService.addPractitioner(practitioner, user, file);
            } catch (ParseException e) {
                logger.error("Error while saving HealthPractitioner : "+e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PractitionerEntity());
    }


}
