package com.birujung.memoride_frontend.request;

import com.birujung.memoride_frontend.TourCard;
import com.birujung.memoride_frontend.helper.LoginData;
import com.birujung.memoride_frontend.model.UserData;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseAPIService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/login")
    Call<ResponseBody> login(@Body LoginData loginData);

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/register")
    Call<ResponseBody> register(@Body UserData userData);

    @GET("api/v1/tours/{id}")
    Call<TourCard> getTour(@Path("id") String tourId);

    @GET("api/v1/tours/")
    Call<JsonObject> getTours();

    @GET("api/v1/tours/search/getFeaturedTours")
    Call<JsonObject> getFeaturedTours();
}
