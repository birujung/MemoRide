package com.birujung.memoride_frontend;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birujung.memoride_frontend.helper.useFetch;
import com.birujung.memoride_frontend.model.TourData;
import com.birujung.memoride_frontend.request.BaseAPIService;
import com.birujung.memoride_frontend.request.BaseAPIUtils;
import com.birujung.memoride_frontend.request.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllToursActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ToursAdapter toursAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tours);

        recyclerView = findViewById(R.id.tours_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toursAdapter = new ToursAdapter();
        recyclerView.setAdapter(toursAdapter);

        fetchTours();
    }

    private void fetchTours() {
        BaseAPIService apiService = BaseAPIUtils.getAPIService();
        Call<JsonObject> call = apiService.getTours();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject != null) {
                        if (jsonObject.get("success").getAsBoolean()) {
                            int count = jsonObject.get("count").getAsInt();
                            if (count > 0) {
                                List<TourData> tours = parseTourData(jsonObject);
                                // Update the RecyclerView adapter with the tour list
                                toursAdapter.setTours(tours);
                            } else {
                                Toast.makeText(AllToursActivity.this, "No tours found in the response", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            String message = jsonObject.get("message").getAsString();
                            Toast.makeText(AllToursActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AllToursActivity.this, "Error: Response body is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AllToursActivity.this, "Error: Fetching data failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("error", "Error is: " + t);
                Toast.makeText(AllToursActivity.this, "Error: Fetching data failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<TourData> parseTourData(JsonObject jsonObject) {
        List<TourData> tours = new ArrayList<>();

        if (jsonObject.has("data")) {
            JsonArray jsonArray = jsonObject.getAsJsonArray("data");

            for (JsonElement jsonElement : jsonArray) {
                JsonObject tourObject = jsonElement.getAsJsonObject();

                // Extract the tour properties from the JSON object
                int id = tourObject.get("id").getAsInt();
                String title = tourObject.get("title").getAsString();
                String location = tourObject.get("city").getAsString();
                double price = Double.parseDouble(tourObject.get("price").getAsString());
                boolean isFeatured = tourObject.get("featured").getAsBoolean();

                // Create a TourData object with the extracted properties
                TourData tour = new TourData(id, title, location, price, isFeatured);
                tours.add(tour);
            }
        }

        return tours;
    }
}
