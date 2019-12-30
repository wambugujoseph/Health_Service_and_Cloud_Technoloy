package com.cloud.health.authorizationservice.util;

import com.cloud.health.authorizationservice.model.OauthClientDetails;
import com.cloud.health.authorizationservice.model.TempOauthClient;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/27/2019
 * Time: 11:36 AM
 * Project: CloudHealthAuthorizationService
 */

public interface OauthClientDetailsService {

    TempOauthClient addOauthClientDetails(OauthClientDetails oauthClientDetails);
    boolean ifExitsClientId(String client_id);
}
