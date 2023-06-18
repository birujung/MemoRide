package com.birujung.memoride_frontend.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.birujung.memoride_frontend.model.UserData;
import com.google.gson.Gson;

public class AuthContext {
    private static final String PREF_NAME = "AuthPrefs";
    private static final String KEY_USER = "user";

    private SharedPreferences sharedPreferences;

    public AuthContext(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUser(UserData user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String userDataJson = gson.toJson(user);
        editor.putString(KEY_USER, userDataJson);
        editor.apply();
    }

    public void setRole(String role) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("role", role);
        editor.apply();
    }


    public UserData getUser() {
        Gson gson = new Gson();
        String userDataJson = sharedPreferences.getString(KEY_USER, null);
        if (userDataJson != null) {
            UserData userData = gson.fromJson(userDataJson, UserData.class);
            String role = sharedPreferences.getString("role", null);
            if (role != null) {
                userData.setRole(role);
            }
            return userData;
        }
        return null;
    }

    public void clearUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return getUser() != null;
    }
}
