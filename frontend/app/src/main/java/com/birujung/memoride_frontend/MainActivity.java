package com.birujung.memoride_frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.birujung.memoride_frontend.helper.AuthContext;
import com.birujung.memoride_frontend.model.TourData;
import com.birujung.memoride_frontend.model.UserData;
import com.birujung.memoride_frontend.request.BaseAPIService;
import com.birujung.memoride_frontend.request.BaseAPIUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView usernameTextView;
    private TextView membershipLevelTextView;
    private RecyclerView recyclerView;
    private ToursAdapter toursAdapter;
    private AuthContext authContext;
    Context mContext;

    @SuppressLint("MissingInflatedId")
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

        usernameTextView = findViewById(R.id.username_text_main);
        membershipLevelTextView = findViewById(R.id.membership_level_text_main);

        // Get user data from shared preferences
        UserData userData = authContext.getUser();

        if (userData != null) {
            // Set user data to views
            usernameTextView.setText(userData.getUsername());
            membershipLevelTextView.setText(userData.getMembershipLevel());
        } else {
            Toast.makeText(MainActivity.this, "User profile data not found", Toast.LENGTH_SHORT).show();
        }

        recyclerView = findViewById(R.id.featured_tours_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toursAdapter = new ToursAdapter();
        recyclerView.setAdapter(toursAdapter);

        fetchTours();
    }

    private void fetchTours() {
        BaseAPIService apiService = BaseAPIUtils.getAPIService();
        Call<JsonObject> call = apiService.getFeaturedTours();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject != null) {
                        boolean success = jsonObject.get("success").getAsBoolean();
                        if (success) {
                            JsonArray data = jsonObject.getAsJsonArray("data");
                            int count = data.size();
                            if (count > 0) {
                                List<TourData> tours = parseTourData(data);
                                // Update the RecyclerView adapter with the featured tour list
                                toursAdapter.setTours(tours);
                            } else {
                                Toast.makeText(MainActivity.this, "No tours found in the response", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            String message = jsonObject.get("message").getAsString();
                            Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Error: Response body is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: Fetching data failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("error", "Error is: " + t);
                Toast.makeText(MainActivity.this, "Error: Fetching data failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<TourData> parseTourData(JsonArray jsonArray) {
        List<TourData> tours = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject tourObject = jsonElement.getAsJsonObject();

            boolean isFeatured = tourObject.get("featured").getAsBoolean();

            // Only include the tour if it is featured
            if (isFeatured) {
                // Extract the other tour properties from the JSON object
                int id = tourObject.get("id").getAsInt();
                String title = tourObject.get("title").getAsString();
                String location = tourObject.get("city").getAsString();
                double price = Double.parseDouble(tourObject.get("price").getAsString());

                // Create a TourData object with the extracted properties
                TourData tour = new TourData(id, title, location, price, isFeatured);
                tours.add(tour);
            }
        }

        return tours;
    }
}