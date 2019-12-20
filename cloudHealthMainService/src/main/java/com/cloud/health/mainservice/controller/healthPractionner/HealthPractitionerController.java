package com.cloud.health.mainservice.controller.healthPractionner;

import com.cloud.health.mainservice.model.TempUser;
import com.cloud.health.mainservice.model.entity.User;
import com.cloud.health.mainservice.model.entity.UserProfile;
import com.cloud.health.mainservice.service.filestorage.StorageServiceImpl;
import com.cloud.health.mainservice.service.repositoryService.PractitionnerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 12/18/2019
 * Time: 4:31 AM
 * Project: cloudHealthMainService
 */

@RestController
@RequestMapping("api/v1")
public class HealthPractitionerController {

    @Autowired
    private StorageServiceImpl storageService;

    @Autowired
    private PractitionnerRepositoryService practitionnerRepositoryService;


    /**
     * @param file profile picture of client
     * @param redirectAttributes to be contained in the redirect Url
     * @return
     */
    @PostMapping(value = "/register/client", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> registerClient(@RequestParam("file") MultipartFile file, @ModelAttribute TempUser tempUser, RedirectAttributes redirectAttributes) {
        boolean isProfileUploaded = storageService.store(file);
        if (!(tempUser == null)) {
            if(isProfileUploaded){
                boolean userAdded =practitionnerRepositoryService.addClient(getCompleteUserObject(tempUser,file.getOriginalFilename()));
                if(userAdded){
                    return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"Created\"}");
                }
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{\"message\":\"Not Created\"}");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Empty User Information\"}");
    }

    /**
     * @param tempUser to be save in Json Format
     * @return Respose status
     */
    @PostMapping(value = "/register/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerClient(@RequestBody TempUser tempUser){
        if (!(tempUser == null)) {
            boolean userAdded =practitionnerRepositoryService.addClient(getCompleteUserObject(tempUser,null));
            if(userAdded){
                return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"Created\"}");
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{\"message\":\"Not Created\"}");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Empty User Information\"}");
    }

    @PostMapping(value = "/create/clientprofile")
    public ResponseEntity<Object> addClientProfile(@RequestBody UserProfile userProfile){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private User getCompleteUserObject(TempUser tempUser, String profileUrl) {
        User user = new User();
        user.setAccessContractsByUserId(null);
        user.setAccessLogsByUserId(null);
        user.setEmergenciesByUserId(null);
        user.setPatientsByUserId(null);
        user.setPractionnersByUserId(null);
        user.setUserProfilesByUserId(null);
        user.setFirstName(tempUser.getFirstName());
        user.setMiddleName(tempUser.getMiddleName());
        user.setSirName(tempUser.getSirName());
        user.setEmail(tempUser.getEmail());
        user.setPhoneNumber(tempUser.getPhoneNumber());
        user.setGender(tempUser.getGender());
        user.setNationality(tempUser.getNationality());
        user.setProfilePhotoUrl(profileUrl);
        user.setUserId(tempUser.getUserId());
        user.setDob(getSqlDate(tempUser.getDob()));
        return user;
    }

    private Date getSqlDate(String date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyy");
            java.util.Date utilDate = simpleDateFormat.parse(date);
            return  new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
