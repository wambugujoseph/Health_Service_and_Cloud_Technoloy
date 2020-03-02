package com.cloud.medical.records.client_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cloud.medical.records.client_app.service.AuthoriseUserService;
import com.cloud.medical.records.client_app.util.AppProgressDialog;
import com.cloud.medical.records.client_app.util.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.cloud.medical.records.client_app.model.Constant.ACCESS_TOKEN_FILE;
import static com.cloud.medical.records.client_app.model.Constant.ACCESS_TOKEN_URL;

public class LoginActivity extends AppCompatActivity {

    private Context context;
    private AppProgressDialog authorizationAlertDialogue;
    TextView textView, username, passwordTxtView;
    Button login;

    private AuthoriseUserService authoriseUserService = new AuthoriseUserService("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();

        //progress bar
        authorizationAlertDialogue = new AppProgressDialog(this);

        textView = findViewById( R.id.open_sign_up);
        login = findViewById(R.id.cirLoginButton);
        username = findViewById(R.id.editTextEmail);
        passwordTxtView = findViewById(R.id.editTextPassword);

        login.setOnClickListener(v -> {
            String clientName = username.getText().toString();
            String clientPassword = passwordTxtView.getText().toString();

            login(clientName,clientPassword, authoriseUserService.getLoginAuthorizationHeaders(), context);
        });

        textView.setOnClickListener(v -> {
            Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(register);
        });

    }

    public void saveAccessToken(JSONObject authToken){

        String token = authToken.toString().trim();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(ACCESS_TOKEN_FILE, MODE_PRIVATE);
            fos.write(token.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String loadAccessToken(){

        FileInputStream fis = null;
        try {
            fis =openFileInput(ACCESS_TOKEN_FILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String token;

            while ((token = br.readLine()) != null) {
                sb.append(token).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "++++++++++++++ Failed";
    }

    /**
     * Initiates  login to obtain access token from auth server
     * @param clientUsername client's
     * @param clientPassword client's
     * @param resourceHeaders resource authorisation
     */
    private void login(String clientUsername, String clientPassword, Map<String, String> resourceHeaders, Context mContext){

        authorizationAlertDialogue.show();

        StringRequest authRequest = new StringRequest(Request.Method.POST, ACCESS_TOKEN_URL, response -> {

            if (response != null){
                try {
                    JSONObject oAuthToken = new JSONObject(response);
                    /*save the token for future user*/
                    saveAccessToken(oAuthToken);

                    /*start main activity after login*/
                    Intent mainActivityIntent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(mainActivityIntent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this,"Authorization error: "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
                authorizationAlertDialogue.dismiss();
            }else {
                Toast.makeText(LoginActivity.this,"Authorization: \n Null response",Toast.LENGTH_LONG).show();
            }

        }, error -> {
                Toast.makeText(LoginActivity.this, "Wrong username or password \n" +error.networkResponse, Toast.LENGTH_LONG).show();
                authorizationAlertDialogue.dismiss();
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return resourceHeaders;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> requestParam = new HashMap<>();
                requestParam.put("grant_type", "password");
                requestParam.put("username", clientUsername);
                requestParam.put("password", clientPassword);

                return requestParam;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(authRequest);
    }
    
}
