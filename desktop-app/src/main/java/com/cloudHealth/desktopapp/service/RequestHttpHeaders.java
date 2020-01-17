package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.service.AuthorizeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.cloudHealth.desktopapp.config.Constant.CLIENT_ID;
import static com.cloudHealth.desktopapp.config.Constant.CLIENT_SECRET;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 1/17/2020
 * Time: 6:25 PM
 * Project: desktop-app
 */

@Service
public class RequestHttpHeaders {

    @Autowired
    private AuthorizeUserService authorizeUserService;

    public HttpHeaders getHTTPRequestHeaders() {
        return new HttpHeaders() {{
            setContentType(MediaType.APPLICATION_JSON);
            setBearerAuth(authorizeUserService.getUserAccessToken());
        }};
    }
}
