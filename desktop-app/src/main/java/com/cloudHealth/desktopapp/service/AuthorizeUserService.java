package com.cloudHealth.desktopapp.service;

import com.cloudHealth.desktopapp.model.UserAccessToken;
import org.apache.catalina.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Autowired RestTemplate restTemplate;
    @Autowired UserAccessToken userAccessToken;


    @Value("${access-token-json-file}")
    String fileName;

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
            RestTemplate tokenRestTemplate = new RestTemplate();
            ResponseEntity<UserAccessToken> response = tokenRestTemplate
                    .postForEntity(ACCESS_TOKEN_URL, requestBody, UserAccessToken.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            storeUserAccessToken(Objects.requireNonNull(response.getBody()));
            return true;
        }else
            return false;

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
            Files.write(Paths.get(fileName), jsonObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserAccessToken(){
        try {
            FileReader reader = new FileReader(fileName);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return jsonObject.get("access_token").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private  String getUserRefreshToken(){
        try {
            FileReader reader = new FileReader(fileName);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return jsonObject.get("refresh_token").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
