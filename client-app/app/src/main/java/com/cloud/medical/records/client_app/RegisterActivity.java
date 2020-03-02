package com.cloud.medical.records.client_app;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cloud.medical.records.client_app.model.TempUser;
import com.cloud.medical.records.client_app.util.AppProgressDialog;
import com.cloud.medical.records.client_app.util.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.cloud.medical.records.client_app.model.Constant.REGISTER_URL;
import static com.cloud.medical.records.client_app.util.Validator.isValidEmail;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private EditText username, phoneNumber, email, idNumber, password, confirmPassword;
    private TextView signIn;
    private Button register;
    private AppProgressDialog registerProgressDialog;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.context = getApplicationContext();
        //initialize controls
         initControls();

         //progress Bar
        registerProgressDialog = new AppProgressDialog(this);

         register.setOnClickListener(v -> {
             TempUser user = getClientInfo();
             if (user != null)
                 registerClient(user);
         });
    }

    private void initControls(){
        username =  findViewById(R.id.username_txt);
        phoneNumber = findViewById(R.id.phone_number_txt);
        email = findViewById(R.id.email_txt);
        idNumber = findViewById(R.id.id_number_txt);
        password = findViewById(R.id.password_txt);
        confirmPassword = findViewById(R.id.confirm_password_txt);
        signIn = findViewById(R.id.sign_in_txt);
        register = findViewById(R.id.register_btn);
    }

    private TempUser getClientInfo(){

        if (!(username.getText().toString().isEmpty() && phoneNumber.getText().toString().isEmpty() && email.getText().toString().isEmpty() && idNumber.getText().toString().isEmpty()
        && password.getText().toString().isEmpty() && confirmPassword.getText().toString().isEmpty())) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())){
                TempUser user = new TempUser();
                if (isValidEmail(email.getText())){
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setPhoneNumber(phoneNumber.getText().toString());
                    user.setUserId(idNumber.getText().toString());
                    user.setUsername(username.getText().toString());
                    return user;
                }
                else
                    email.setTextColor(Color.parseColor("#FF0000"));
            }else
                confirmPassword.setTextColor(Color.parseColor("#FF0000"));
        }else
            getAlert("Empty fields ! \n All field are required").show();


        return new TempUser();

    }


    private void registerClient(TempUser user){
        registerProgressDialog.show();
        try {
            String str = new Gson().toJson(user);
            JSONObject requestObj = new JSONObject(str);

            Log.d(TAG,"Message to be sent : "+requestObj.toString());

            JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, requestObj, response -> {
                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                registerProgressDialog.dismiss();

            }, error -> {
                registerProgressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Failed to register !\n" + error, Toast.LENGTH_SHORT).show();
            });

            VolleySingleton.getInstance(context).addToRequestQueue(registerRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            registerProgressDialog.dismiss();
        }
    }

    private AlertDialog getAlert(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        return  alert11;
    }
}
