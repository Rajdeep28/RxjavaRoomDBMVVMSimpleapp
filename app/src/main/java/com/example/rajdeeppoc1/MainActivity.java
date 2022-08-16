package com.example.rajdeeppoc1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    private static final int CONTENT_VIEW_ID = 10101010;
    BottomNavigationView bottomNavigationView;
    HomeFragment firstFragment = new HomeFragment();
    PeopleFragment secondFragment = new PeopleFragment();
    // ThirdFragment thirdFragment = new ThirdFragment();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentPages();
    }

    private void fragmentPages() {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment,"HomeFragment").commit();
    }
    
}
