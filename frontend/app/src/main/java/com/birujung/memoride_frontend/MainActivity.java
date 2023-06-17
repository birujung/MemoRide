package com.birujung.memoride_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.birujung.memoride_frontend.helper.AuthContext;

public class MainActivity extends AppCompatActivity {
    private AuthContext authContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authContext = new AuthContext(this);

        // Check if the user is logged in
        if (authContext.isLoggedIn()) {
            // User is logged in, perform necessary actions
            String username = authContext.getUser();
        } else {
            // User is not logged in, redirect to the login screen
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
    }
}