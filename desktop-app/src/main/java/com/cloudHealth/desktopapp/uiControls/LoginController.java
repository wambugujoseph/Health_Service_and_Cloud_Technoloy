package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.service.AuthorizeUserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    @FXML private JFXTextField userName;
    @FXML private JFXPasswordField password;
    @FXML private JFXButton LogInSubmitBtn;
    @FXML private JFXCheckBox rememberMe;
    @FXML private Hyperlink ForgetPwdLink;
    @FXML private Hyperlink signUpLink;

    @Autowired
    AuthorizeUserService authorizeUserService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LogInSubmitBtn.setOnAction(event ->
                logIn());

    }

    public void logIn(){
        String username = this.userName.getText();
        String pwd = this.password.getText();
        authorizeUserService.userLogin(username, pwd);
        this.userName.clear();
        this.password.clear();
    }
}
