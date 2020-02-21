package com.cloudHealth.desktopapp;


import com.cloudHealth.desktopapp.util.StageReadyEvent;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class DesktopAppApplication {
    public static void main(String[] args) {
       // SpringApplication.run(BootfulApplication.class, args);
        Application.launch(BootfulApplication.class, args);
    }

}
