package com.ebeyonds.myapplication.ui.base;

import android.content.Intent;
import android.os.Bundle;

import com.ebeyonds.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.ebeyonds.myapplication.ui.main.SectionsPagerAdapter;
import com.pixplicity.easyprefs.library.Prefs;

public class MainActivity extends AppCompatActivity {

    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.setIS_LOGGED_IN(Prefs.getInt(IS_LOGGED_IN,0));
        mainActivityViewModel.getIS_LOGGED_IN().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==0){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }


}