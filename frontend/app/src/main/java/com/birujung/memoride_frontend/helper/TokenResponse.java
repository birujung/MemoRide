package com.birujung.memoride_frontend.helper;

import com.birujung.memoride_frontend.model.UserData;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("data")
    private UserData userData;

    public String getToken() {
        return token;
    }

    public UserData getUserData() {
        return userData;
    }
}
