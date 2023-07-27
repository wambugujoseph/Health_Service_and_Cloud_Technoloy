package com.cloud.medical.records.client_app.resource;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;

import com.cloud.medical.records.client_app.R;
import com.cloud.medical.records.client_app.resource.profile.PatientAboutFragment;
import com.cloud.medical.records.client_app.resource.profile.PatientRecordGrantFragment;
import com.cloud.medical.records.client_app.resource.profile.ProfilePageAdapter;
import com.cloud.medical.records.client_app.service.AuthoriseUserService;
import com.cloud.medical.records.client_app.util.authUtil.JWTAuthUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    View view;
    private TabLayout profileTabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private TextView clientName, clientEmail;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setTabs(inflater, container);
        //initialize  view components
        initViewComponents();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

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

    private void initViewComponents(){
        clientEmail = view.findViewById(R.id.profile_user_email);
        clientName = view.findViewById(R.id.profile_user_name);

        populateViewComponents();
    }

    @SuppressLint("SetTextI18n")
    private void populateViewComponents(){
        AuthoriseUserService userService = new AuthoriseUserService();
        try {
            clientEmail.setText(userService.getUserEmail(userService.getUserAccessToken(new JSONObject(JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT))));
            clientName.setText("@"+userService.getUsername(userService.getUserAccessToken(new JSONObject(JWTAuthUtil.JWT_ACCESS_TOKEN_OBJECT))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
