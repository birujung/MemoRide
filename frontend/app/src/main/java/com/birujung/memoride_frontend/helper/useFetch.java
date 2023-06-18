package com.birujung.memoride_frontend.helper;

import android.content.Context;
import android.util.Log;

import com.birujung.memoride_frontend.TourCard;
import com.birujung.memoride_frontend.request.BaseAPIService;
import com.birujung.memoride_frontend.request.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class useFetch {

    public static <T> void fetchTour(Context context, String url, String tourId, final FetchCallback<T> callback) {
        Retrofit retrofit = RetrofitClient.getClient(url);
        BaseAPIService service = retrofit.create(BaseAPIService.class);

        Call<TourCard> call = service.getTour(tourId);
        call.enqueue(new Callback<TourCard>() {
            @Override
            public void onResponse(Call<TourCard> call, Response<TourCard> response) {
                if (response.isSuccessful()) {
                    TourCard tourCard = response.body();
                    callback.onSuccess(tourCard);
                } else {
                    Log.e("useFetch", "Response not successful. Code: " + response.code());
                    callback.onError("Failed to fetch tour data");
                }
            }

            @Override
            public void onFailure(Call<TourCard> call, Throwable t) {
                Log.e("useFetch", "Request failed: " + t.getMessage());
                callback.onError("Request failed");
            }
        });
    }

    public interface FetchCallback<T> {
        void onSuccess(TourCard data);
        void onError(String errorMessage);
    }
}
