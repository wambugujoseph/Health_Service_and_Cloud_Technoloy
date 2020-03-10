package com.cloud.medical.records.client_app.model;

public class Constant {

    public static final String CLIENT_ID ="mobile";
    public static final String CLIENT_SECRET ="pin";

    public static final String  ACCESS_TOKEN_FILE = "user_access_token.json";

    private static final String MAIN_SERVICE_BASE_URL ="http://192.168.43.133:8080/api/v1";
    private static final String AUTH_SERVER_BASE_URL ="http://192.168.43.133:9191";

    public static final String CLIENT_PROFILE_URL = MAIN_SERVICE_BASE_URL+"/clientProfile";
    public static final String CLIENT_ACCESS_GRANT_URL = MAIN_SERVICE_BASE_URL+"/client/accessContract";
    public static final String CLIENT_NOTIFICATIONS = MAIN_SERVICE_BASE_URL+"/client/notifications";

    public static final String ACCESS_TOKEN_URL = AUTH_SERVER_BASE_URL+"/oauth/token";
    public static final String REGISTER_URL = AUTH_SERVER_BASE_URL+"/register";

}
