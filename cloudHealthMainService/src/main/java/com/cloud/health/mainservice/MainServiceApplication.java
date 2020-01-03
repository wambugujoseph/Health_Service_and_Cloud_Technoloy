package com.cloud.health.mainservice;

import com.cloud.health.mainservice.model.HealthPractitioner;
import com.cloud.health.mainservice.model.entity.PersonalDoctorEntityPK;
import com.cloud.health.mainservice.model.entity.PractitionerEntity;
import com.cloud.health.mainservice.model.entity.RealTimeDataEntity;
import com.cloud.health.mainservice.service.filestorage.ProfileFileStorageService;
import com.cloud.health.mainservice.service.repositoryService.ClientService;
import com.cloud.health.mainservice.service.repositoryService.PractitionerMedicalRecordService;
import com.cloud.health.mainservice.service.repositoryService.PractitionerRepositoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
public class MainServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainServiceApplication.class, args);
    }

    @Bean
    public ProfileFileStorageService getProfileFileStorageService(){
        return new ProfileFileStorageService();
    }

    @Bean
    public PractitionerRepositoryService getPractitionerRepoService(){
        return new PractitionerRepositoryService();
    }

    @Bean
    public HealthPractitioner getHealthPractitioner(){
        return new HealthPractitioner();
    }

    @Bean
    public PractitionerEntity getPractitionerEntity(){
        return new PractitionerEntity();
    }

    @Bean
    PractitionerMedicalRecordService getPractitionerMedicalRecordService(){
        return new PractitionerMedicalRecordService();
    }

    @Bean
    public ClientService gerClientService(){
        return new ClientService();
    }

    @Bean
    public PersonalDoctorEntityPK getPersonalDoctorEntityPK(){
        return new PersonalDoctorEntityPK();
    }

    @Bean
    public RealTimeDataEntity getRealTimeDataEntity(){
        return new RealTimeDataEntity();
    }
}
