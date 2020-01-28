package com.cloudHealth.desktopapp.util;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 1/15/2020
 * Time: 11:20 PM
 * Project: desktop-app
 */

public class StageReadyEvent extends ApplicationEvent {

    public Stage getStage() {
        return (Stage) getSource();
    }
    public StageReadyEvent(Stage source) {
        super(source);
    }
}
