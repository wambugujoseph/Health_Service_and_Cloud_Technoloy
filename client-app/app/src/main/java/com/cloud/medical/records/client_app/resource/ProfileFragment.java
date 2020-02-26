package com.cloud.medical.records.client_app.resource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;

import com.cloud.medical.records.client_app.R;
import com.cloud.medical.records.client_app.resource.profile.PatientAboutFragment;
import com.cloud.medical.records.client_app.resource.profile.PatientRecordGrantFragment;
import com.cloud.medical.records.client_app.resource.profile.ProfilePageAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {

    View view;
    private TabLayout profileTabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setTabs(inflater, container);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    private void setTabs(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);
        profileTabLayout = view.findViewById(R.id.profile_fragment_tabs);
        viewPager = view.findViewById(R.id.profile_fragment_viewpager);
        assert getFragmentManager() != null;
        ProfilePageAdapter adapter = new ProfilePageAdapter(getFragmentManager(), Lifecycle.State.RESUMED.ordinal());

        adapter.addFragment(new PatientAboutFragment(),"About");
        adapter.addFragment(new PatientRecordGrantFragment(),"Access Grants");
        viewPager.setAdapter(adapter);
        profileTabLayout.setupWithViewPager(viewPager);
    }
}
