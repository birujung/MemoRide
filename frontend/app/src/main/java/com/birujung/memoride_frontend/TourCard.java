package com.birujung.memoride_frontend;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.birujung.memoride_frontend.model.TourData;

import java.util.List;

public class TourCard extends LinearLayout {
    private TextView tourTitleTextView;
    private TextView tourLocationTextView;
    private TextView tourRatingTextView;
    private TextView tourPriceTextView;

    public TourCard(Context context) {
        super(context);
        initializeViews(context);
    }

    public TourCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public TourCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_tour_card, this);

        tourTitleTextView = findViewById(R.id.tour_title);
        tourLocationTextView = findViewById(R.id.tour_location);
        tourRatingTextView = findViewById(R.id.tour_rating);
//        tourPriceTextView = findViewById(R.id.tour_price);
    }

    public void setTour(TourData tour) {
        tourTitleTextView.setText(tour.getTitle());
        tourLocationTextView.setText(tour.getCity());
//        tourRatingTextView.setText(String.format("%.1f", tour.getAverageRating()));
        tourPriceTextView.setText("$" + tour.getPrice() + " /per person");

//        if (tour.getReviews().size() > 0) {
//            tourRatingTextView.append(" (" + tour.getReviews().size() + ")");
//        }

        if (tour.isFeatured()) {
            setBackgroundColor(Color.YELLOW);
        } else {
            setBackgroundColor(Color.WHITE);
        }

        // Load tour image using Picasso or any other image loading library
//        Picasso.get().load(tour.getPhotoUrl()).into(imageView);
    }
}
