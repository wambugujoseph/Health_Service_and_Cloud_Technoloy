package com.cloud.health.mainservice;

import com.cloud.health.mainservice.service.filestorage.StorageServiceImpl;
import com.cloud.health.mainservice.service.repositoryService.PractitionnerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MainServiceApplication {

    @Autowired
   private StorageServiceImpl storageService;

    public static void main(String[] args) {

        SpringApplication.run(MainServiceApplication.class, args);
    }

    @Bean
    public StorageServiceImpl getStorageService(){
        return new StorageServiceImpl();
    }

    @Bean
    public PractitionnerRepositoryService getPractitionnerRepoService(){
        return new PractitionnerRepositoryService();
    }

}
