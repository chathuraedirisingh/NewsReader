package com.ebeyonds.myapplication.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ebeyonds.myapplication.R;
import com.pixplicity.easyprefs.library.Prefs;

public class LoginActivity extends AppCompatActivity {

    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LoginActivityViewModel loginActivityViewModel = ViewModelProviders.of(this).get(LoginActivityViewModel.class);

        loginActivityViewModel.setIS_LOGGED_IN(Prefs.getInt(IS_LOGGED_IN,0));

        loginActivityViewModel.getIS_LOGGED_IN().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==1){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        final EditText username = findViewById(R.id.inputName);
        final EditText password = findViewById(R.id.inputPassword);
        Button login = findViewById(R.id.login);

        final TextView exUsername = findViewById(R.id.username);
        final TextView exPassword = findViewById(R.id.password);

        loginActivityViewModel.getUsername().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                exUsername.setText(s);
            }
        });

        loginActivityViewModel.getPassword().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                exPassword.setText(s);
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginActivityViewModel.setUsername(editable.toString());
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginActivityViewModel.setPassword(editable.toString());
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((username.getText().toString()!=null) && (username.getText().toString()!=null)){
                    Prefs.putInt(IS_LOGGED_IN,1);
                    Prefs.putString(USERNAME,username.getText().toString());
                    Prefs.putString(PASSWORD,password.getText().toString());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}
