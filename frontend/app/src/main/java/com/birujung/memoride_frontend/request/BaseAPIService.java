package com.birujung.memoride_frontend.request;

import com.birujung.memoride_frontend.helper.LoginData;
import com.birujung.memoride_frontend.helper.TokenResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseAPIService {
    @POST("api/v1/auth/login")
    Call<ResponseBody> login(@Body LoginData loginData);

}
