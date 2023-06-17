package com.birujung.memoride_frontend.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthContext {
    private static final String PREF_NAME = "AuthPrefs";
    private static final String KEY_USERNAME = "username";

    private SharedPreferences sharedPreferences;

    public AuthContext(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUser(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public String getUser() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public void clearUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USERNAME);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return getUser() != null;
    }
}

