package com.cloud.medical.records.client_app.resource.profile;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cloud.medical.records.client_app.R;
import com.cloud.medical.records.client_app.model.User;
import com.cloud.medical.records.client_app.model.UserProfile;
import com.cloud.medical.records.client_app.service.AuthoriseUserService;
import com.cloud.medical.records.client_app.util.VolleySingleton;
import com.cloud.medical.records.client_app.util.authUtil.JWTAuthUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;

import static com.cloud.medical.records.client_app.model.Constant.CLIENT_PROFILE_URL;

public class PatientAboutFragment extends Fragment {

    View view;

    private TextView name, idNumber, phoneNumber, dateOfBirth, gender, age, bloodGroup, specialNeeds, healthComplication, insurance;
    private AuthoriseUserService authoriseUserService = new AuthoriseUserService();

    public PatientAboutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.patient_about,container, false);

        //initialize components
        initUiComponents();
        return view;
    }

    private void initUiComponents(){

        name  = view.findViewById(R.id.about_name);
        idNumber = view.findViewById(R.id.about_id_number);
        phoneNumber = view.findViewById(R.id.about_phone_number);
        dateOfBirth = view.findViewById(R.id.about_dob);
        gender = view.findViewById(R.id.about_gender);
        age = view.findViewById(R.id.about_age);
        bloodGroup = view.findViewById(R.id.about_blood_group);
        specialNeeds = view.findViewById(R.id.about_special_needs);
        healthComplication = view.findViewById(R.id.about_health_complication);
        insurance = view.findViewById(R.id.about_insurance);

        //display client about
        fetchAboutPatientInfo();
    }

    private void  fetchAboutPatientInfo(){
        String userEmailOrId = null;
        try {
            userEmailOrId = authoriseUserService.getUserEmail(authoriseUserService.getUserAccessToken(new JSONObject(JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Request patient about information
        JsonObjectRequest  request = new JsonObjectRequest(Request.Method.GET, CLIENT_PROFILE_URL+"/"+userEmailOrId, null, response -> {

            if (response != null){

                try {
                    UserProfile userProfile = new Gson().fromJson(response.toString(), UserProfile.class);
                    //Toast.makeText(getContext(),"Object: " +response.toString(), Toast.LENGTH_LONG).show();
                    //update ui
                    updateClientAboutUi(userProfile);

                } catch (Exception e) {
                    Toast.makeText(getContext(),"Error: " +e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }

        }, error -> {
            Toast.makeText(getContext(),"Could not Load about info: "+error,Toast.LENGTH_LONG).show();
        }){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return new AuthoriseUserService().getAuthorizationBearerToken();
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    public void updateClientAboutUi(UserProfile profile){

        User user = profile.getOwner();
        String clientName = user.getSirName()+" "+user.getFirstName()+" "+user.getMiddleName();
        name.setText(clientName);
        idNumber.setText(user.getUserId());
        phoneNumber.setText(user.getPhoneNumber());
        dateOfBirth.setText(user.getDob());
        gender.setText(user.getGender());
        age.setText(new Date().toString());
        bloodGroup.setText(profile.getBloodGroup());
        specialNeeds.setText(profile.getSpecialNeeds());
        healthComplication.setText(profile.getComplication());
        insurance.setText(profile.getInsuranceInformation());
    }

}
