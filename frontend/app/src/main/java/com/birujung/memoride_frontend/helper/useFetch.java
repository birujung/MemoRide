package com.birujung.memoride_frontend.helper;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class useFetch extends AsyncTask<String, Void, String> {
    private Context context;
    private OnDataFetchListener listener;

    public useFetch(Context context, OnDataFetchListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        String result = "";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            result = stringBuilder.toString();

            bufferedReader.close();
            inputStream.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            listener.onDataFetched(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onDataFetchError(e.getMessage());
        }
    }

    public interface OnDataFetchListener {
        void onDataFetched(JSONArray data);
        void onDataFetchError(String errorMessage);
    }
}
