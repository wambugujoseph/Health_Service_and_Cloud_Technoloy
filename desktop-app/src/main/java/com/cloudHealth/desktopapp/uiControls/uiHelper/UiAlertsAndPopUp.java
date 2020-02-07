package com.cloudHealth.desktopapp.uiControls.uiHelper;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 1/22/2020
 * Time: 12:18 AM
 * Project: desktop-app
 */

@Component
public class UiAlertsAndPopUp {

    public  Alert showAlert(Alert.AlertType alertType, String contentText, String title, String headerText, Node graphic){
        Alert alert = new Alert(alertType);
        alert.setContentText(contentText);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setGraphic(graphic);
        return alert;
    }
}
