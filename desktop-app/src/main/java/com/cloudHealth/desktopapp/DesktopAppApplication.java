package com.cloudHealth.desktopapp;


import com.cloudHealth.desktopapp.util.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class DesktopAppApplication extends Application {
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
