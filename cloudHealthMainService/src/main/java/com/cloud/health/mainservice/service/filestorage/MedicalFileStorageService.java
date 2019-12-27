package com.cloud.health.mainservice.service.filestorage;

import com.cloud.health.mainservice.util.FileStorage;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

import static com.cloud.health.mainservice.util.Constant.MEDICAL_FILE_STORAGE_LOCATION;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 12:33 PM
 * Project: cloudHealthMainService
 */

@Service
public class MedicalFileStorageService implements FileStorage {
    @Override
    public void init() {
        File path = new File(MEDICAL_FILE_STORAGE_LOCATION);
        if(!(path.exists())){
            path.mkdirs();
        }
    }

    @Override
    public String store(MultipartFile file) {
        try {
            init();
            StringBuilder fileName =  new StringBuilder();
            String name = file.getOriginalFilename();
            int endIndex = Objects.requireNonNull(name).lastIndexOf(".");
            String modifiedName = name.replace(name.substring(0,endIndex),"MEDICAL-FILE-"+ LocalDateTime.now().toString())
                    .replaceAll(":","-");
            Path fileNamePath = Paths.get(getMedicalFileStorage(), modifiedName);
            fileName.append(modifiedName);
            Files.write(fileNamePath, file.getBytes());
            return modifiedName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public ResponseEntity<Resource> load(String fileName) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    private String getMedicalFileStorage() {
        return MEDICAL_FILE_STORAGE_LOCATION;
    }
}
