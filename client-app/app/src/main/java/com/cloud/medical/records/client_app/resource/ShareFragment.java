package com.cloud.medical.records.client_app.resource;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cloud.medical.records.client_app.R;
import com.cloud.medical.records.client_app.model.AccessContract;
import com.cloud.medical.records.client_app.model.PersonalHealthPractitioner;
import com.cloud.medical.records.client_app.service.AuthoriseUserService;
import com.cloud.medical.records.client_app.util.AppProgressDialog;
import com.cloud.medical.records.client_app.util.Relationship;
import com.cloud.medical.records.client_app.util.VolleySingleton;
import com.cloud.medical.records.client_app.util.authUtil.JWTAuthUtil;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static com.cloud.medical.records.client_app.model.Constant.CLIENT_ACCESS_GRANT_URL;
import static com.cloud.medical.records.client_app.model.Constant.CLIENT_PERSONAL_PRACTITIONER;

public class ShareFragment extends Fragment {

    private View view;
    private EditText shareWithEmailOrId, personalPractitionerEmail;
    private SwitchMaterial medicalShareSwitch;
    private Button personalMedicalPractitionerBtn;
    private Spinner medicalShareGrants;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_share,container,false);
        shareWithEmailOrId = view.findViewById(R.id.personalPractitionerEmail);
        personalMedicalPractitionerBtn = view.findViewById(R.id.personalMedicalPractitionerBtn);
        personalPractitionerEmail = view.findViewById(R.id.personalPractitionerEmail);
        medicalShareGrants = view.findViewById(R.id.medicalShareGrants);

        actionEvent();
        getMedicalAccessGrantEmails();
        return view;
    }

    private void  actionEvent(){
        personalMedicalPractitionerBtn.setOnClickListener(v -> uploadPersonalDoctor());
    }

    private void uploadPersonalDoctor(){
        PersonalHealthPractitioner practitioner = new PersonalHealthPractitioner();
        AuthoriseUserService userService = new AuthoriseUserService();
        if (!(personalPractitionerEmail.getText().toString().isEmpty())) {
            try {
                practitioner.setClientIdOrEmail(userService.getUserEmail(userService.getUserAccessToken(new JSONObject(JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT))));
                practitioner.setPractitionerUserIdOrEmail(personalPractitionerEmail.getText().toString());
                JSONObject jsonObject = new JSONObject(new Gson().toJson(practitioner));

               AppProgressDialog progressDialog = new AppProgressDialog(getContext());
               progressDialog.show();
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, CLIENT_PERSONAL_PRACTITIONER, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Upload personal Medical practitioner: ",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Failed to upload personal Medical practitioner: "+error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }){
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return new AuthoriseUserService().getAuthorizationBearerToken();
                    }
                };
                VolleySingleton.getInstance(getContext()).addToRequestQueue(request);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Error: " +e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setMedicalShareGrants(AccessContract[]  accessGrants){
        try {
            List<String> accessGrantEmails = null;
            for (AccessContract accessContract: accessGrants) {
                accessGrantEmails.add(accessContract.getUserByUserId().getEmail());
            }
            ArrayAdapter<Relationship> arrayAdapter;
            assert accessGrantEmails != null;
            arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,  Relationship.values());
            medicalShareGrants.setAdapter(arrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMedicalAccessGrantEmails(){
        String emailOrId = "";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, CLIENT_ACCESS_GRANT_URL + "/" + emailOrId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                AccessContract[] accessContract = new Gson().fromJson(response.toString(), AccessContract[].class);
                //update the lists
                setMedicalShareGrants(accessContract);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Faield To loade the Access Grant "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return new AuthoriseUserService().getAuthorizationBearerToken();
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(arrayRequest);
    }
}
