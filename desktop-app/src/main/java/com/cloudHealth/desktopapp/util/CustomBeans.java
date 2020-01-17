package com.cloudHealth.desktopapp.util;

import com.cloudHealth.desktopapp.model.UserAccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 1/17/2020
 * Time: 12:57 PM
 * Project: desktop-app
 */

@Component
public class CustomBeans {

    @Bean
    public UserAccessToken getUserAccessToken(){
        return new UserAccessToken();
    }
}
