package com.cloud.health.mainservice.util.auth;

import com.cloud.health.mainservice.model.CustomPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Sunday
 * Date: 12/29/2019
 * Time: 11:10 PM
 * Project: cloudHealthMainService
 */

@Component
public class UserAuthenticationDetails {

    public String getUserEmail(){
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)){
//
//        }
        CustomPrincipal user = (CustomPrincipal) authentication.getPrincipal();
        return "+++" + user.getEmail();
        //return "";
    }
}
