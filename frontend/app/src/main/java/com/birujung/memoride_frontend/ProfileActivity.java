package com.birujung.memoride_frontend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.birujung.memoride_frontend.helper.AuthContext;
import com.birujung.memoride_frontend.model.UserData;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePhotoImageView;
    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView roleTextView;
    private TextView fullNameTextView;
    private TextView genderTextView;
    private TextView phoneNumTextView;
    private TextView membershipLevelTextView;

    private AuthContext authContext;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        authContext = new AuthContext(this);
        mContext = this;

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

        // Initialize views
        usernameTextView = findViewById(R.id.username_text);
        emailTextView = findViewById(R.id.email_text);
        roleTextView = findViewById(R.id.role_text);
        fullNameTextView = findViewById(R.id.full_name_text);
        genderTextView = findViewById(R.id.gender_text);
        phoneNumTextView = findViewById(R.id.phone_num_text);
        membershipLevelTextView = findViewById(R.id.membership_level_text);

        // Get user data from shared preferences
        UserData userData = authContext.getUser();

        if (userData != null) {
            // Set user data to views
            usernameTextView.setText(userData.getUsername());
            emailTextView.setText(userData.getEmail());
            roleTextView.setText(userData.getRole());
            fullNameTextView.setText(userData.getFullName());
            genderTextView.setText(userData.getGender());
            phoneNumTextView.setText(userData.getPhoneNumber());
            membershipLevelTextView.setText(userData.getMembershipLevel());
        } else {
            Toast.makeText(ProfileActivity.this, "User profile data not found", Toast.LENGTH_SHORT).show();
        }
    }
}
