package com.birujung.memoride_frontend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.birujung.memoride_frontend.R;
import com.birujung.memoride_frontend.TourCard;
import com.birujung.memoride_frontend.helper.useFetch;
import com.birujung.memoride_frontend.helper.useFetch.FetchCallback;
import com.birujung.memoride_frontend.model.TourData;
import com.birujung.memoride_frontend.request.BaseAPIService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AllToursActivity extends Activity {

    private LinearLayout tourCardContainer;
    private TextView loadingText;
    private TextView errorText;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tours);
        mContext = this;

        tourCardContainer = findViewById(R.id.tour_card_container);
        loadingText = findViewById(R.id.loading_text);
        errorText = findViewById(R.id.error_text);

        fetchTours();

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

    private void fetchTours() {
        // Clear existing tour cards
        tourCardContainer.removeAllViews();

        // Create a static list of dummy TourData objects
        List<TourData> tours = createDummyTours();

        // Hide loading and error texts
        loadingText.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);

        // Populate tour cards
        for (TourData tour : tours) {
            View tourCardView = getLayoutInflater().inflate(R.layout.activity_tour_card, tourCardContainer, false);
            TourCard tourCard = new TourCard(this, tourCardView);
            tourCard.bindTourData(tour);
            tourCardContainer.addView(tourCardView);
        }
    }

    private List<TourData> createDummyTours() {
        List<TourData> tours = new ArrayList<>();

        // Create 5 dummy TourData objects and add them to the list
        for (int i = 1; i <= 5; i++) {
            TourData tour = new TourData();
            tour.setTitle("Tour " + i);
            tour.setCity("City " + i);
            tour.setPhoto("dummy_photo_url_" + i);
            tours.add(tour);
        }

        return tours;
    }
}
