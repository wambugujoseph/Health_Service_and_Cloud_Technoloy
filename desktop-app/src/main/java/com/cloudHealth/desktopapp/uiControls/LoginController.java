package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.StageLauncher;
import com.cloudHealth.desktopapp.service.AuthorizeUserService;
import com.cloudHealth.desktopapp.service.RequestHttpHeaders;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/16/2020
 * Time: 11:11 PM
 * Project: desktop-app
 */

@Component
public class LoginController implements Initializable {

    @FXML private AnchorPane loginPage;
    @FXML private JFXTextField userName;
    @FXML private JFXPasswordField password;
    @FXML private JFXButton LogInSubmitBtn;
    @FXML private StackPane loginDialogue;
    @FXML private JFXCheckBox rememberMe;
    @FXML private Hyperlink ForgetPwdLink;
    @FXML private Hyperlink signUpLink;
    @FXML private JFXProgressBar loginProgressBar;
    @FXML private AnchorPane progressAnchorPane;

    @Autowired
    AuthorizeUserService authorizeUserService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RequestHttpHeaders requestHttpHeaders;

    private JFXDialog dialog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LogInSubmitBtn.setOnAction(event -> {
            Platform.runLater(this::logIn);
//            ResponseEntity<String> response;
//            try {
//                HttpEntity<String> requestBody = new HttpEntity<>("", requestHttpHeaders.getHTTPRequestHeaders());
//                response = restTemplate.
//                        exchange("http://localhost:8080/api/v1/username", HttpMethod.GET, requestBody, String.class);
//                System.out.println(response.getBody());
//            } catch (HttpClientErrorException e) {
//                System.out.println(e.getMessage());
//            }
        });

        loginPage.setOnMouseClicked(event -> dialog.close());

    }

    private void logIn(){

        JFXProgressBar progressBar = new JFXProgressBar();
        progressBar.setPrefWidth(progressAnchorPane.getWidth());
        progressBar.setPrefHeight(progressAnchorPane.getHeight()/4);
        String username = this.userName.getText();
        String pwd = this.password.getText();
        progressAnchorPane.getChildren().add(progressBar);
        boolean authorised;
        authorised = authorizeUserService.userLogin(username, pwd);
        if (!authorised){
            JFXDialogLayout content = new JFXDialogLayout();
            Text body = new Text("Wrong Username or password");
            body.setStyle("-fx-background-color: none");
            content.setStyle("-fx-background-color: #ffae42");
            content.setBody(body);
            dialog = new JFXDialog(loginDialogue, content, JFXDialog.DialogTransition.BOTTOM);
            dialog.show();
        }else
            StageLauncher.stageLogin.close();
        progressAnchorPane.getChildren().remove(0);
        this.userName.clear();
        this.password.clear();
    }
}
