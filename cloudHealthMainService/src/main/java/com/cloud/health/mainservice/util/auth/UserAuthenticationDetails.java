package com.cloud.health.mainservice.util.auth;

import com.cloud.health.mainservice.model.CustomPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.util.Map;

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

    @Autowired
    public TokenStore tokenStore;

    public CustomPrincipal getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String principal = (String) authentication.getPrincipal();
            Map<String, Object> additionalInfo = getAdditionalUserInfo(authentication);
            String userEmail = (String) additionalInfo.get("email");

            return new CustomPrincipal(authentication.getName(), userEmail);
        }
        return new CustomPrincipal(null, null);
    }

    private Map<String, Object> getAdditionalUserInfo(Authentication authentication) {
        OAuth2AuthenticationDetails detail = (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(detail.getTokenValue());
        return accessToken.getAdditionalInformation();
    }
}
