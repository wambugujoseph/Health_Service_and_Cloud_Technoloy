package com.cloudHealth.desktopapp;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 1/15/2020
 * Time: 11:25 PM
 * Project: desktop-app
 */

@SpringBootApplication
public class BootfulApplication {

    public static void main(String[] args) {
        //SpringApplication.run(JavafxApplication.class, args);
        Application.launch(DesktopAppApplication.class, args);
    }
}
