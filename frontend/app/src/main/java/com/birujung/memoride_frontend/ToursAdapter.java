package com.birujung.memoride_frontend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.birujung.memoride_frontend.model.TourData;

import java.util.ArrayList;
import java.util.List;

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.ViewHolder> {
    private List<TourData> tours = new ArrayList<>();

    public void setTours(List<TourData> tours) {
        this.tours = tours;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TourData tour = tours.get(position);
        holder.bind(tour);
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tourTitleTextView;
        private TextView tourLocationTextView;
        private TextView tourPriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tourTitleTextView = itemView.findViewById(R.id.tour_title);
            tourLocationTextView = itemView.findViewById(R.id.tour_location);
            tourPriceTextView = itemView.findViewById(R.id.tour_price);
        }

        public void bind(TourData tour) {
            tourTitleTextView.setText(tour.getTitle());
            tourLocationTextView.setText(tour.getCity());
            tourPriceTextView.setText("$" + tour.getPrice() + " /per person");
        }
    }
}
