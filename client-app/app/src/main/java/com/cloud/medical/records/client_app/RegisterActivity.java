package com.cloud.medical.records.client_app;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cloud.medical.records.client_app.model.TempUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, phoneNumber, email, idNumber, password, confirmPassword;
    private TextView signIn;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //initialize controls
         initControls();

         register.setOnClickListener(v -> {
             registerClient();
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

    private void registerClient(){

        if (!(username.getText().toString().isEmpty() && phoneNumber.getText().toString().isEmpty() && email.getText().toString().isEmpty() && idNumber.getText().toString().isEmpty()
        && password.getText().toString().isEmpty() && confirmPassword.getText().toString().isEmpty())) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())){
                TempUser user = new TempUser();
                if (isValidEmail(email.getText().toString())){
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setPhoneNumber(phoneNumber.getText().toString());
                    user.setUserId(idNumber.getText().toString());
                }
                else
                    email.setTextColor(Color.parseColor("#FF0000"));
            }else
                confirmPassword.setTextColor(Color.parseColor("#FF0000"));
        }else
            getAlert("Empty fields ! \n All field are required").show();

    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
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
