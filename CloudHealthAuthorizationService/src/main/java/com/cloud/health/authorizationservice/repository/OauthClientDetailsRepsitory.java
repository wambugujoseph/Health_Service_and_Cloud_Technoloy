package com.cloud.health.authorizationservice.repository;

import com.cloud.health.authorizationservice.model.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/27/2019
 * Time: 11:20 AM
 * Project: CloudHealthAuthorizationService
 */

public interface OauthClientDetailsRepsitory extends JpaRepository<OauthClientDetails, String> {
    Optional<OauthClientDetails> findByClientId(String clientId);
}