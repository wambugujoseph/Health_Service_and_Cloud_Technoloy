package com.cloud.health.authorizationservice.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/27/2019
 * Time: 11:04 AM
 * Project: CloudHealthAuthorizationService
 */

@Entity
@Table(name = "oauth_client_details", schema = "oauthdb", catalog = "")
public class OauthClientDetails {
    private String clientId;
    private String clientSecret;
    private String webServerRedirectUri;
    private String scope;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String resourceIds;
    private String authorizedGrantTypes;
    private String authorities;
    private String additionalInformation;
    private String autoapprove;
    private Integer numberUser;
    private String username;
    private String applicationName;

    @Id
    @Column(name = "client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Basic
    @Column(name = "web_server_redirect_uri")
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    @Basic
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "access_token_validity")
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    @Basic
    @Column(name = "refresh_token_validity")
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    @Basic
    @Column(name = "resource_ids")
    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Basic
    @Column(name = "authorized_grant_types")
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Basic
    @Column(name = "authorities")
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Basic
    @Column(name = "additional_information")
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Basic
    @Column(name = "autoapprove")
    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OauthClientDetails that = (OauthClientDetails) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(clientSecret, that.clientSecret) &&
                Objects.equals(webServerRedirectUri, that.webServerRedirectUri) &&
                Objects.equals(scope, that.scope) &&
                Objects.equals(accessTokenValidity, that.accessTokenValidity) &&
                Objects.equals(refreshTokenValidity, that.refreshTokenValidity) &&
                Objects.equals(resourceIds, that.resourceIds) &&
                Objects.equals(authorizedGrantTypes, that.authorizedGrantTypes) &&
                Objects.equals(authorities, that.authorities) &&
                Objects.equals(additionalInformation, that.additionalInformation) &&
                Objects.equals(autoapprove, that.autoapprove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientSecret, webServerRedirectUri, scope, accessTokenValidity, refreshTokenValidity, resourceIds, authorizedGrantTypes, authorities, additionalInformation, autoapprove);
    }

    @Basic
    @Column(name = "number_user")
    public Integer getNumberUser() {
        return numberUser;
    }

    public void setNumberUser(Integer numberUser) {
        this.numberUser = numberUser;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "application_name")
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
