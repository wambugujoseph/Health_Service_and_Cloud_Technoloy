package com.cloud.medical.records.client_app.resource.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cloud.medical.records.client_app.R;

public class PatientRecordGrantFragment extends Fragment {

    View view;

    public PatientRecordGrantFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.record_access_grant,container, false);
        return view;
    }
}
