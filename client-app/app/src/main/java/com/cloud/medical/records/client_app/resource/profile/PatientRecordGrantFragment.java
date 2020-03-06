package com.cloud.medical.records.client_app.resource.profile;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cloud.medical.records.client_app.R;
import com.cloud.medical.records.client_app.model.AccessContract;
import com.cloud.medical.records.client_app.model.User;
import com.cloud.medical.records.client_app.service.AccessGrantRecyclerViewAdapter;
import com.cloud.medical.records.client_app.service.AuthoriseUserService;
import com.cloud.medical.records.client_app.util.AppProgressDialog;
import com.cloud.medical.records.client_app.util.Relationship;
import com.cloud.medical.records.client_app.util.VolleySingleton;
import com.cloud.medical.records.client_app.util.authUtil.JWTAuthUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static com.cloud.medical.records.client_app.model.Constant.CLIENT_ACCESS_GRANT_URL;

public class PatientRecordGrantFragment extends Fragment {

    private static final String TAG = "PatientRecordGrantFragment";
    private View view, grantFormView;


    private ArrayList<String> name =  new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    private ArrayList<String> phoneNumber = new ArrayList<>();
    private ArrayList<String> idNumber = new ArrayList<>();
    private ArrayList<String> imageName = new ArrayList<>();

    private FloatingActionButton floatingActionButton;

    private EditText grantEmailOrID, grantRelationOthers;
    private Button grantAdd, grantCancel;
    private MaterialRadioButton grantHigh, grantMedium, grantLow;
    private Switch grantAcvateSwitch;
    private Spinner grantRelationSpinner;
    private RadioGroup grantRadioGroup;

    private AlertDialog dialog;

    private AccessContract[] accessContract = null;

    public PatientRecordGrantFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.record_access_grant,container, false);
        //load patient medical record access grant
        loadMedicalAccessList();
        //
        initUiComponents();
        //Action events
        actionEvents();
        //Get Access Grant List
        getAccessContract();
        return view;
    }

    private void initUiComponents() {
        floatingActionButton = view.findViewById(R.id.accessGrantFloatingButton);

    }

    private void actionEvents(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddAccessGrantForm();
            }
        });
    }

    private void showAddAccessGrantForm(){
        AlertDialog.Builder alertDialog;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getContext()),R.style.Theme_MaterialComponents_Dialog_Alert);
        }else {
            alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        }

        LayoutInflater inflater =  getLayoutInflater();

        grantFormView = inflater.inflate(R.layout.layout_access_grant_add,null);
        alertDialog.setView(grantFormView);
        alertDialog.setCancelable(true);
        this.dialog = alertDialog.create();
        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);;
        this.dialog.show();

        grantFormInitAndActionEvent();
    }

    private void grantFormInitAndActionEvent() {
        this.grantRadioGroup = grantFormView.findViewById(R.id.grantLevelRadioGroup);
        this.grantRelationOthers = grantFormView.findViewById(R.id.grantRelationshipOthers);
        this.grantRelationOthers.setEnabled(false);
        this.grantCancel = grantFormView.findViewById(R.id.grantCancelButton);
        this.grantRelationSpinner = grantFormView.findViewById(R.id.grantRelationshipSpinner);
        this.grantAdd  = grantFormView.findViewById(R.id.grantAddButton);
        this.grantEmailOrID = grantFormView.findViewById(R.id.grantEmailOrId);
        this.grantHigh = grantFormView.findViewById(R.id.grantHigh);
        this.grantLow =grantFormView.findViewById(R.id.grantLow);
        this.grantMedium = grantFormView.findViewById(R.id.grantMedium);
        this.grantAcvateSwitch =grantFormView.findViewById(R.id.grantActive);
        Relationship[] relationship = Relationship.values();
        ArrayAdapter<Relationship> arrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.support_simple_spinner_dropdown_item, relationship);
        grantRelationSpinner.setAdapter(arrayAdapter);

        grantRelationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equalsIgnoreCase(Relationship.Others.toString()))
                    grantRelationOthers.setEnabled(true);
                else
                    grantRelationOthers.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    if (grantRelationOthers.isEnabled())
                        grantRelationOthers.setEnabled(false);
            }
        });

        grantCancel.setOnClickListener(v -> {
            if (this.dialog.isShowing()){
                this.dialog.cancel();
            }
        });

        grantAdd.setOnClickListener(v-> postClientAccessGrant(createAccessGrantObject()));
    }

    private void postClientAccessGrant(JSONObject clientAccessGrant){

        AppProgressDialog appProgressDialog = new AppProgressDialog(getContext());
        appProgressDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, CLIENT_ACCESS_GRANT_URL, clientAccessGrant, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getContext(),"Successfully add", Toast.LENGTH_SHORT).show();
                //add access list

                appProgressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Cloud not save access grant the user may not be registered in the system ! \nError"+error.getMessage(),
                        Toast.LENGTH_SHORT).show();
                appProgressDialog.dismiss();
            }
        }){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public Map<String, String> getHeaders() {
                return new AuthoriseUserService().getAuthorizationBearerToken();
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    private JSONObject createAccessGrantObject(){

        try {
            if (!(grantEmailOrID.getText().toString().isEmpty() && grantRelationSpinner.getSelectedItem().toString().isEmpty())){
                if (grantHigh.isChecked() || grantMedium.isChecked() || grantLow.isChecked()) {
                    JSONObject clientAccessGrant = new JSONObject();

                    if (grantRelationSpinner.getSelectedItem().toString().equalsIgnoreCase(Relationship.Others.toString())
                            && !(grantRelationOthers.getText().toString().isEmpty())) {
                        clientAccessGrant.put("relationship", grantRelationOthers.getText().toString());
                    } else
                        clientAccessGrant.put("relationship", grantRelationSpinner.getSelectedItem().toString());

                    if (grantHigh.isChecked()) {
                        clientAccessGrant.put("accessLevel", "High");
                    }
                    else if (grantMedium.isChecked()){
                        clientAccessGrant.put("accessLevel", "Medium");
                    }
                    else {
                        clientAccessGrant.put("accessLevel", "Low");
                    }

                    AuthoriseUserService service = new AuthoriseUserService();
                    clientAccessGrant.put("userIdOrEmail",service
                            .getUserEmail(service.getUserAccessToken(new JSONObject(JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT))));

                    clientAccessGrant.put("grantedToUserIdOrEmail",grantEmailOrID.getText().toString());
//                    if (grantAcvateSwitch.isChecked()) {
////                        clientAccessGrant.put("active",1);
////                    }else
////                        clientAccessGrant.put("active",0);

                    return clientAccessGrant;
                }
                Toast.makeText(getContext(), "Provide All the data test", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getContext(), "Provide All the data required", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

    private void loadMedicalAccessList(){

        // initialize the access list RecyclerView
        initAccessGrantRecyclerView();
    }

    private void getAccessContract(){

        String emailOrId = "";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, CLIENT_ACCESS_GRANT_URL + "/" + emailOrId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                accessContract = new Gson().fromJson(response.toString(), AccessContract[].class);
                //update the lists
                updateAccessGrantLists(accessContract);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Faield To loade the Access Grant "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
               return new AuthoriseUserService().getAuthorizationBearerToken();
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(arrayRequest);

    }

    private void updateAccessGrantLists(AccessContract[] accessContract){

        for (AccessContract contract : accessContract ) {
            User user = contract.getUserByGrantedTo();
            name.add(user.getFirstName()+" "+user.getMiddleName()+" "+user.getSirName());
            email.add(user.getEmail());
            phoneNumber.add(user.getPhoneNumber());
            idNumber.add(user.getUserId());
        }

        //stage the Access Grant
        initAccessGrantRecyclerView();
    }

    @SuppressLint("LongLogTag")
    private void initAccessGrantRecyclerView(){
        Log.d(TAG, "initAccessGrantRecyclerView: init recyclerView");

        RecyclerView recyclerView = view.findViewById(R.id.access_grand_recycler_view);
        AccessGrantRecyclerViewAdapter  adapter = new AccessGrantRecyclerViewAdapter(
               getContext() ,name, email, phoneNumber,idNumber,imageName);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
