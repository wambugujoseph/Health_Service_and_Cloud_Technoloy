package com.cloud.health.mainservice.service.filestorage;

import com.cloud.health.mainservice.util.FileStorage;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

import static com.cloud.health.mainservice.util.Constant.PROFILE_PIC_STORAGE_LOCATION;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 12/19/2019
 * Time: 12:02 AM
 * Project: cloudHealthMainService
 */

public class ProfileFileStorageService implements FileStorage {

    @Override
    @PostConstruct
    public void init() {
        File path = new File(PROFILE_PIC_STORAGE_LOCATION);
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
            String modifiedName = name.replace(name.substring(0,endIndex),"PROFILE-PIC-"+ LocalDateTime.now().toString())
                    .replaceAll(":","-");
            Path fileNamePath = Paths.get(getProfileImageStorage(), modifiedName);
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
        Resource file = loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    public String getProfileImageStorage(){
        return PROFILE_PIC_STORAGE_LOCATION;
    }
}
