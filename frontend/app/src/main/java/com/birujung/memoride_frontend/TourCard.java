package com.birujung.memoride_frontend;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.birujung.memoride_frontend.model.TourData;
import com.bumptech.glide.Glide;

public class TourCard extends FrameLayout {
    String url = "https://picsum.photos/300/200?random";
    private ImageView tourImage;
    private TextView tourTitle;
    private TextView tourRating;
    private TextView tourLocation;
    private Button bookNowButton;

    public TourCard(Context context, View view) {
        super(context);
        init(view.getContext());
    }

    public TourCard(Context context, AttributeSet attrs, View view) {
        super(context, attrs);
        init(view.getContext());
    }

    public TourCard(Context context, AttributeSet attrs, int defStyleAttr, View view) {
        super(context, attrs, defStyleAttr);
        init(view.getContext());
    }

    private void init(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_tour_card, this, true);
        tourImage = findViewById(R.id.tour_img_image);
        tourLocation = findViewById(R.id.tour_location);
        tourRating = findViewById(R.id.tour_rating);
        tourTitle = findViewById(R.id.tour_title);
        bookNowButton = findViewById(R.id.book_now_button);
    }

    public void bindTourData(TourData tour) {
        // Set tour data to the views
        Glide.with(getContext())
                .load(tour.getPhoto())
                .placeholder(Drawable.createFromPath(url)) // Placeholder image while loading
                .into(tourImage);

        tourLocation.setText(tour.getCity());
        tourTitle.setText(tour.getTitle());
    }
}
