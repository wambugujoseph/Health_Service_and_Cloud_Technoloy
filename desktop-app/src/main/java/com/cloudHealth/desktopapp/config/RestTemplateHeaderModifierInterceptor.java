package com.cloudHealth.desktopapp.config;

import com.cloudHealth.desktopapp.StageLauncher;
import com.cloudHealth.desktopapp.service.AuthorizeUserService;
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
    private StageLauncher stageLauncher;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(httpRequest,body);

        response.getHeaders().add("Authorization","Bearer "+ authorizeUserService.getUserAccessToken());
        //invoke open Login page if unauthorised
        if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            stageLauncher.LaunchLoginPage(new Stage());
        }
        return response;
    }

}
