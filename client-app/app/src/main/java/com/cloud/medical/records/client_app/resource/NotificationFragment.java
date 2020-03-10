package com.cloud.medical.records.client_app.resource;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cloud.medical.records.client_app.R;
import com.cloud.medical.records.client_app.model.HealthRecNotification;
import com.cloud.medical.records.client_app.model.User;
import com.cloud.medical.records.client_app.service.AuthoriseUserService;
import com.cloud.medical.records.client_app.service.NotificationsRecyclerViewAdapter;
import com.cloud.medical.records.client_app.util.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.cloud.medical.records.client_app.model.Constant.CLIENT_NOTIFICATIONS;

public class NotificationFragment extends Fragment {

    private HealthRecNotification[] healthRecNotifications;

    private View view;

    private ArrayList<String> name =  new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    private ArrayList<String> datePosted = new ArrayList<>();
    private ArrayList<String> imageName = new ArrayList<>();
    private ArrayList<String> message = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_notification,container,false);

        getNotifications();

        return view;
    }

    private void getNotifications(){

        JsonArrayRequest request =  new JsonArrayRequest(Request.Method.GET, CLIENT_NOTIFICATIONS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                healthRecNotifications = new Gson().fromJson(response.toString(), HealthRecNotification[].class);

                //populate notification View
                populatedNotificationRecyclerView(healthRecNotifications);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Could not load notifications \n"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return new AuthoriseUserService().getAuthorizationBearerToken();
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(request);

    }

    private void  populatedNotificationRecyclerView(HealthRecNotification[] healthRecNotifications){

        for (HealthRecNotification healthRecNotification : healthRecNotifications){

            User user = healthRecNotification.getUserByNotificationFrom();

            name.add(user.getFirstName() +" "+user.getSirName());
            email.add(user.getEmail());
            datePosted.add(new Date().toString());
            imageName.add("");
            message.add(healthRecNotification.getMessage());

        }

        initNotificationsRecyclerView();
    }

    private  void initNotificationsRecyclerView(){
        RecyclerView recyclerView =  view.findViewById(R.id.notificationsRecyclerView);
        NotificationsRecyclerViewAdapter adapter =  new NotificationsRecyclerViewAdapter(
                getContext(), name, email,datePosted,imageName,message);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
