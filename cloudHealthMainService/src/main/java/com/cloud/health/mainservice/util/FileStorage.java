package com.cloud.health.mainservice.util;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 12/18/2019
 * Time: 11:48 PM
 * Project: cloudHealthMainService
 */

public interface FileStorage {

    void init();
    String store(MultipartFile File);
    Stream<Path> loadAll();
    ResponseEntity<Resource> load(String fileName);
    Resource loadAsResource(String filename);
    void deleteAll();
}
