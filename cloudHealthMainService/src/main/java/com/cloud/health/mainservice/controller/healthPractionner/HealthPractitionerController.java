package com.cloud.health.mainservice.controller.healthPractionner;

import com.cloud.health.mainservice.model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    private String uploadDirectory = "";
    
    @RequestMapping(value = "/register_client", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Object> registerUser(User user, @RequestParam("file") MultipartFile file) throws IOException {
        StringBuilder fileName =  new StringBuilder();
        Path fileNamePath = Paths.get(uploadDirectory, file.getOriginalFilename());
        fileName.append(file.getOriginalFilename());
        Files.write(fileNamePath, file.getBytes());

        return new ResponseEntity<>("Data has been uploaded", HttpStatus.CREATED);
    }

}
