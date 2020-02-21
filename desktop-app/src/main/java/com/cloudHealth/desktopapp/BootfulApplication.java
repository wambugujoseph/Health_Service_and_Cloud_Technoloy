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
public class BootfulApplication extends Application  {
    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {

        this.context = new SpringApplicationBuilder()
                .sources(BootfulApplication.class)
                .initializers()
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        this.context.stop();
        Platform.exit();
    }
}
