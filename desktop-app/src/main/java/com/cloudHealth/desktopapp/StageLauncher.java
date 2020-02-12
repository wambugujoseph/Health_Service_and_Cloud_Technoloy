package com.cloudHealth.desktopapp;

import com.cloudHealth.desktopapp.service.AuthorizeUserService;
import com.cloudHealth.desktopapp.util.StageReadyEvent;
import com.cloudHealth.desktopapp.util.auth.JwtAuthUtil;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Tuesday
 * Date: 1/14/2020
 * Time: 11:41 PM
 * Project: javafx
 */

@Component
public class StageLauncher implements ApplicationListener<StageReadyEvent> {
    @Autowired
    private AuthorizeUserService authorizeUserService;
    private final String applicationTitle;
    private final Resource fxml;
    private final Resource mainCss, loginPage;
    private final ApplicationContext ac;
    public static Stage stageLogin;


    StageLauncher(
            @Value("${spring.application.ui.title}") String applicationTitle,
            @Value("classpath:/templates/home.fxml") Resource resource,
            @Value("classpath:/static/main.css") Resource mainCss,
            @Value("classpath:/templates/logIn.fxml") Resource loginPage,
            ApplicationContext ac){
        this.applicationTitle = applicationTitle;
        this.fxml = resource;
        this.ac = ac;
        this.mainCss = mainCss;
        this.loginPage = loginPage;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {

        try {
            Stage stage = stageReadyEvent.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.mainCss.getURL().toString());
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle(this.applicationTitle);
            stage.show();

            if (/*false*/authorizeUserService.checkIsUserTokenExpired()){
                LaunchLoginPage(stage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LaunchLoginPage(Stage mainStage) throws IOException {
        URL loginPageURL = this.loginPage.getURL();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPageURL);
        fxmlLoader.setControllerFactory(ac::getBean);
        Parent root = fxmlLoader.load();
        Scene loginPageScene = new Scene(root);
        stageLogin = new Stage();
        stageLogin.setScene(loginPageScene);
        stageLogin.initModality(Modality.APPLICATION_MODAL);
        stageLogin.setResizable(false);
        stageLogin.setTitle("");
        stageLogin.show();
        stageLogin.setOnCloseRequest(event -> {
            mainStage.close();
        });
    }
}

