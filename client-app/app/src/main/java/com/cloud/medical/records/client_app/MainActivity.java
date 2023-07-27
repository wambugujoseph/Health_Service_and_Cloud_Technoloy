package com.cloud.medical.records.client_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cloud.medical.records.client_app.resource.HomeFragment;
import com.cloud.medical.records.client_app.resource.NotificationFragment;
import com.cloud.medical.records.client_app.resource.ProfileFragment;
import com.cloud.medical.records.client_app.resource.ShareFragment;
import com.cloud.medical.records.client_app.service.AuthoriseUserService;
import com.cloud.medical.records.client_app.util.authUtil.JWTAuthUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.micrometer.core.instrument.util.IOUtils;

import static com.cloud.medical.records.client_app.model.Constant.ACCESS_TOKEN_FILE;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkIsUserAuthorised()){
            Intent logInIntent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(logInIntent);
        }else {
            setContentView(R.layout.activity_main);
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            bottomNav.setOnNavigationItemSelectedListener(navListener);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new HomeFragment())
                    .commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case  R.id.nav_share:
                        selectedFragment = new ShareFragment();
                        break;
                    case R.id.nav_notification:
                        selectedFragment = new NotificationFragment();
                        break;
                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, selectedFragment)
                        .commit();
                return true;
            };

    /**
     * Reads private key;
     * @return private key string
     */
    private String getPublicKey(){
        InputStream inputStream = null;
        try {
            inputStream =  getAssets().open("public.txt");
             JWTAuthUtil.PUBLIC_KEY = IOUtils.toString(inputStream);
            return JWTAuthUtil.PUBLIC_KEY;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void loadAccessToken(){

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

            //init access token
            JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT = sb.toString();

            //Toast.makeText(this,""+JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT,Toast.LENGTH_LONG).show();

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

    }

    public boolean checkIsUserAuthorised(){

        try {
            loadAccessToken();
            JSONObject jwtObject = new JSONObject(JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT);
            AuthoriseUserService authoriseUserService = new AuthoriseUserService(getPublicKey());
            return authoriseUserService.checkIsUserTokenExpired(jwtObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
