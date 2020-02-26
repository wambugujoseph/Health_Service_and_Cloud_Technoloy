package com.cloud.medical.records.client_app.service;


import android.util.Base64;

import com.cloud.medical.records.client_app.model.AccessToken;
import com.cloud.medical.records.client_app.util.authUtil.JWTAuthUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.cloud.medical.records.client_app.model.Constant.CLIENT_ID;
import static com.cloud.medical.records.client_app.model.Constant.CLIENT_SECRET;

public class AuthoriseUserService {

    public String publicKey;

    public AuthoriseUserService(String publicKey) {
        this.publicKey = publicKey;
    }

    private JSONObject tokenJSONObject(AccessToken accessToken){
        JSONObject jsonObject = new JSONObject();
        Map<Object, Object> token = new HashMap<>();
        token.put("access_token", accessToken.getAccess_token());
        token.put("token_type", accessToken.getToken_type());
        token.put("refresh_token", accessToken.getRefresh_token());
        token.put("expires_in", accessToken.getExpires_in());
        token.put("email", accessToken.getEmail());
        token.put("jti", accessToken.getJti());
        try {
            jsonObject.put("token",token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getUserAccessToken(JSONObject accessToken){
        try {
            return accessToken.get("access_token").toString().trim();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkIsUserTokenExpired(JSONObject jwtToken){
        JWTAuthUtil jwtAuthUtil =  new JWTAuthUtil(publicKey);
        return jwtAuthUtil.isTokenExpired(getUserAccessToken(jwtToken));
    }



    public  String getUserEmail(String token){

        return  Objects.requireNonNull(Objects.requireNonNull(JWTAuthUtil.extractAllClaims(token)).get("email")).toString();
    }


    private AccessToken jsonObjectToUserAccessTokenConverter(JSONObject jsonObject){
        AccessToken accessToken = new AccessToken();
        try {
            accessToken.setAccess_token(jsonObject.get("access_token").toString());
            accessToken.setRefresh_token(jsonObject.get("refresh_token").toString());
            accessToken.setToken_type(jsonObject.get("token_type").toString());
            accessToken.setExpires_in(Integer.parseInt(jsonObject.get("expires_in").toString()));
            accessToken.setEmail(jsonObject.get("email").toString());
            accessToken.setJti(jsonObject.get("jti").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public Map<String, String> getLoginAuthorizationHeaders(){

        Map<String, String> loginAuthHeaders = new HashMap<>();
        String creds = String.format("%s:%s",CLIENT_ID, CLIENT_SECRET);
        String auth = "Basic "+ Base64.encodeToString(creds.getBytes(),Base64.NO_WRAP);
        //loginAuthHeaders.put("Content-Type","multipart/form-data; charset=UTF-8");
        loginAuthHeaders.put("Authorization",auth );

        return loginAuthHeaders;

    }
}
