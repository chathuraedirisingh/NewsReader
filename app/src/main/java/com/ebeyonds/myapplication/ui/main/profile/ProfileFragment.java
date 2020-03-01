package com.ebeyonds.myapplication.ui.main.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ebeyonds.myapplication.R;
import com.ebeyonds.myapplication.ui.base.LoginActivity;
import com.ebeyonds.myapplication.ui.base.MainActivity;
import com.ebeyonds.myapplication.ui.utils.DialogUtils;
import com.pixplicity.easyprefs.library.Prefs;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private View view;

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.profile_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        mViewModel.setUsername(Prefs.getString(USERNAME,""));


        final TextView profileName = view.findViewById(R.id.username);
        final TextView profileDescription = view.findViewById(R.id.user_desc);

        mViewModel.getUsername().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                profileName.setText(s);
                profileDescription.setText(s);
            }
        });

        Button button = view.findViewById(R.id.signout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.clear().apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
