package com.birujung.memoride_frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.birujung.memoride_frontend.helper.AuthContext;
import com.birujung.memoride_frontend.model.UserData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private AuthContext authContext;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        authContext = new AuthContext(this);

        // Check if the user is logged in
        if (authContext.isLoggedIn()) {
            // User is logged in, perform necessary actions
            UserData user = authContext.getUser();
            String username = user.getUsername();
        } else {
            // User is not logged in, redirect to the login screen
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_all_tours:
                        intent = new Intent(mContext, AllToursActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_profile:
                        intent = new Intent(mContext, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }
}