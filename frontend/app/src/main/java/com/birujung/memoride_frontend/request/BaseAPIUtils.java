package com.birujung.memoride_frontend.request;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseAPIUtils {
    public static final String BASE_URL_API = "http://10.0.2.2:80/";

    public static BaseAPIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseAPIService.class);
    }
}