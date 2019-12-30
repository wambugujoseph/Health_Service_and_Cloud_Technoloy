package com.cloud.health.authorizationservice.service;

import com.cloud.health.authorizationservice.model.OauthClientDetails;
import com.cloud.health.authorizationservice.model.TempOauthClient;
import com.cloud.health.authorizationservice.repository.OauthClientDetailsRepsitory;
import com.cloud.health.authorizationservice.util.AutoGenToken;
import com.cloud.health.authorizationservice.util.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/27/2019
 * Time: 11:34 AM
 * Project: CloudHealthAuthorizationService
 */
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

    @Autowired
    private AutoGenToken autoGenToken;
    @Autowired
    private OauthClientDetailsRepsitory oauthClientDetailsRepsitory;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private String tempToken;

    @Override
    public TempOauthClient addOauthClientDetails(OauthClientDetails oauthClientDetails) {
       setTempToken( autoGenToken.generateRandomString(25));
        while(ifExitsClientId(getTempToken())){
               setTempToken(autoGenToken.generateRandomString(25));
        }
        String client_secret = autoGenToken.generateRandomString(50);

        OauthClientDetails oauthClient = new OauthClientDetails();

        oauthClient.setClientId(getTempToken());
        oauthClient.setClientSecret("{bcrypt}"+bCryptPasswordEncoder.encode(client_secret));
        oauthClient.setWebServerRedirectUri(oauthClientDetails.getWebServerRedirectUri());
        oauthClient.setAccessTokenValidity(oauthClientDetails.getAccessTokenValidity());
        oauthClient.setRefreshTokenValidity(oauthClientDetails.getRefreshTokenValidity());
        oauthClient.setScope(oauthClientDetails.getScope());
        oauthClient.setResourceIds(oauthClientDetails.getResourceIds());
        oauthClient.setAuthorizedGrantTypes(oauthClientDetails.getAuthorizedGrantTypes());
        oauthClient.setAdditionalInformation("{"+oauthClientDetails.getAdditionalInformation()+"}");
        oauthClient.setNumberUser(oauthClientDetails.getNumberUser());
        oauthClient.setUsername(oauthClientDetails.getUsername());
        oauthClient.setApplicationName(oauthClientDetails.getApplicationName());

        OauthClientDetails clientDetails = oauthClientDetailsRepsitory.save(oauthClient);
        return getTempOauthClient(clientDetails, client_secret);
    }

    @Override
    public boolean ifExitsClientId(String client_id) {
       OauthClientDetails oauthClientDetails = oauthClientDetailsRepsitory.findByClientId(client_id).orElse(null);

       if (oauthClientDetails != null){
           return oauthClientDetails.getClientId().equalsIgnoreCase(client_id);
       }
       return false;
    }
    private TempOauthClient getTempOauthClient(OauthClientDetails oauthClientDetails, String clientSecret) {
        return new TempOauthClient(oauthClientDetails.getClientId(),clientSecret, oauthClientDetails.getWebServerRedirectUri(),
                oauthClientDetails.getAccessTokenValidity(), oauthClientDetails.getRefreshTokenValidity(),oauthClientDetails.getScope(), oauthClientDetails.getResourceIds(),
                oauthClientDetails.getAuthorizedGrantTypes(),oauthClientDetails.getAdditionalInformation(),oauthClientDetails.getNumberUser(),oauthClientDetails.getUsername()
        ,oauthClientDetails.getApplicationName());
    }

    private String getTempToken() {
        return tempToken;
    }

    private void setTempToken(String tempToken) {
        this.tempToken = tempToken;
    }

}
