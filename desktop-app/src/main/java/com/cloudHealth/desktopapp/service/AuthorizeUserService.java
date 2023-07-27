package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.StageLauncher;
import com.cloudHealth.desktopapp.model.UserAccessToken;
import com.cloudHealth.desktopapp.uiControls.uiHelper.UiAlertsAndPopUp;
import com.cloudHealth.desktopapp.util.auth.JwtAuthUtil;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.cloudHealth.desktopapp.config.Constant.*;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 1/17/2020
 * Time: 12:11 AM
 * Project: desktop-app
 */

@Service
public class AuthorizeUserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StageLauncher stageLauncher;
    @Autowired
    private JwtAuthUtil jwtAuthUtil;


    public Boolean userLogin(String username, String password){

            HttpHeaders httpHeaders = new HttpHeaders(){{
            setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            setBasicAuth(CLIENT_ID, CLIENT_SECRET, StandardCharsets.US_ASCII);
            }};

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "password");
            body.add("username", username);
            body.add("password", password);

            HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(body,httpHeaders);
        ResponseEntity<UserAccessToken> response;
        try {
            response = restTemplate
                    .postForEntity(ACCESS_TOKEN_URL, requestBody, UserAccessToken.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                UserAccessToken userAccessToken = response.getBody();
                assert userAccessToken != null;
                storeUserAccessToken(userAccessToken);
                return true;
            }else
                return false;
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void storeUserAccessToken(UserAccessToken accessToken){
        JSONObject jsonObject = new JSONObject();
        Map<Object, Object> token = new HashMap<>();
        token.put("access_token", accessToken.getAccess_token());
        token.put("token_type", accessToken.getToken_type());
        token.put("refresh_token", accessToken.getRefresh_token());
        token.put("expires_in", accessToken.getExpires_in());
        token.put("email", accessToken.getEmail());
        token.put("jti", accessToken.getJti());
        jsonObject.putAll(token);
        try {
            Files.write(Paths.get(ACCESS_TOKEN_FILE), jsonObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserAccessToken(){
        try {
            FileReader reader = new FileReader(ACCESS_TOKEN_FILE);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return jsonObject.get("access_token").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "null";
        }
    }

    public boolean checkIsUserTokenExpired(){
        return jwtAuthUtil.isTokenExpired(getUserAccessToken());
    }

    private  String getUserRefreshToken(){
        try {
            FileReader reader = new FileReader(ACCESS_TOKEN_FILE);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return jsonObject.get("refresh_token").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  String getUserEmail(){

        return (String) jwtAuthUtil.extractAllClaims(getUserAccessToken()).get("email");
    }

    public boolean logout(){
        try {
            Map<Object, Object> token = new HashMap<>();
            FileReader reader = new FileReader(ACCESS_TOKEN_FILE);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            UserAccessToken userAccessToken = jsonObjectToUserAccessTokenConverter(jsonObject);
            userAccessToken.setAccess_token("");
            userAccessToken.setRefresh_token("");
            storeUserAccessToken(userAccessToken);
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    private UserAccessToken jsonObjectToUserAccessTokenConverter(JSONObject jsonObject){
        UserAccessToken accessToken = new UserAccessToken();
        accessToken.setAccess_token(jsonObject.get("access_token").toString());
        accessToken.setRefresh_token(jsonObject.get("refresh_token").toString());
        accessToken.setToken_type(jsonObject.get("token_type").toString());
        accessToken.setExpires_in(Integer.parseInt(jsonObject.get("expires_in").toString()));
        accessToken.setEmail(jsonObject.get("email").toString());
        accessToken.setJti(jsonObject.get("jti").toString());
        return accessToken;
    }

    public void unauthorisedNotification(HttpStatus httpStatus){
        try {
            if (httpStatus.equals(HttpStatus.UNAUTHORIZED)){
                System.out.println("++++++++++++++++++++++++++++++++++");
                FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
                icon.setFill(Paint.valueOf("#FB7D00"));
                icon.setSize("50");
                UiAlertsAndPopUp popUp = new UiAlertsAndPopUp();
                Alert alert = popUp.showAlert(Alert.AlertType.WARNING, "You were logged out Please Login and try again","Unauthorised",HttpStatus.UNAUTHORIZED+"",icon );
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

