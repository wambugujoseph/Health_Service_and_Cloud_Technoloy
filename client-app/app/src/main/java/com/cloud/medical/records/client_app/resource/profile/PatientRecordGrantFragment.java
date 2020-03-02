package com.cloud.medical.records.client_app.resource.profile;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cloud.medical.records.client_app.R;
import com.cloud.medical.records.client_app.service.AccessGrantRecyclerViewAdapter;

import java.util.ArrayList;

public class PatientRecordGrantFragment extends Fragment {

    private static final String TAG = "PatientRecordGrantFragm";
    private View view;


    private ArrayList<String> name =  new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    private ArrayList<String> phoneNumber = new ArrayList<>();
    private ArrayList<String> idNumber = new ArrayList<>();
    private ArrayList<String> imageName = new ArrayList<>();

    public PatientRecordGrantFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.record_access_grant,container, false);
        //load patient medical record access grant
        loadMedicalAccessList();
        return view;
    }


    private void loadMedicalAccessList(){

        // initialize the access list RecyclerView
        initAccessGrantRecyclerView();
    }

    private void initAccessGrantRecyclerView(){
        Log.d(TAG, "initAccessGrantRecyclerView: init recyclerView");

        RecyclerView recyclerView = view.findViewById(R.id.access_grand_recycler_view);
        AccessGrantRecyclerViewAdapter  adapter = new AccessGrantRecyclerViewAdapter(
               getContext() ,name, email, phoneNumber,idNumber,imageName);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
