package com.cloudHealth.desktopapp.uiControls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 1/15/2020
 * Time: 12:16 AM
 * Project: javafx
 */

@Component
public class SimpleController implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private TextArea textArea;

    @FXML
    private Button open;


    private final String applicationTitle;
    private final Resource page;
    private final ApplicationContext ac;

    SimpleController(
            @Value("${spring.application.ui.title}") String applicationTitle,
            @Value("classpath:/templates/logIn.fxml") Resource resource, ApplicationContext ac){
        this.applicationTitle = applicationTitle;
        this.page = resource;
        this.ac = ac;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         btn.setOnAction(event -> textArea.setText("its working"));

        open.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                URL url = this.page.getURL();
                FXMLLoader loader = new FXMLLoader(url);
                loader.setControllerFactory(ac::getBean);
                Parent page = loader.load();
                Scene scene = new Scene(page);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
