package com.cloudHealth.desktopapp.config;

import com.cloudHealth.desktopapp.StageLauncher;
import com.cloudHealth.desktopapp.service.AuthorizeUserService;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/16/2020
 * Time: 5:31 PM
 * Project: desktop-app
 */

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    private AuthorizeUserService authorizeUserService = new AuthorizeUserService();
    @Autowired
    private UiAlertsAndPopUp uiAlertsAndPopUp;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(httpRequest,body);

        response.getHeaders().add("Authorization","Bearer "+ authorizeUserService.getUserAccessToken());
        //invoke open Login page if unauthorised

            authorizeUserService.unauthorisedNotification(response.getStatusCode());
        return response;
    }

}
