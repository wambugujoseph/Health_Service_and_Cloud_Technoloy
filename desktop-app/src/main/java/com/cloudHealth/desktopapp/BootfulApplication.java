package com.cloudHealth.desktopapp;

import com.cloudHealth.desktopapp.util.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 1/15/2020
 * Time: 11:25 PM
 * Project: desktop-app
 */

@SpringBootApplication
public class BootfulApplication  {

    public static void main(String[] args) {
        // SpringApplication.run(BootfulApplication.class, args);
        Application.launch(DesktopAppApplication.class, args);
    }

}
