package com.birujung.memoride_frontend.request;

import com.birujung.memoride_frontend.TourCard;
import com.birujung.memoride_frontend.helper.LoginData;
import com.birujung.memoride_frontend.helper.TokenResponse;
import com.birujung.memoride_frontend.model.UserData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseAPIService {
    @POST("api/v1/auth/login")
    Call<UserData> login(@Body LoginData loginData);

    @GET("api/v1/tours/{id}")
    Call<TourCard> getTour(@Path("id") String tourId);
}
