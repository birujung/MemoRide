package com.birujung.memoride_frontend;
import com.birujung.memoride_frontend.helper.AuthContext;
import com.birujung.memoride_frontend.helper.LoginData;
import com.birujung.memoride_frontend.model.UserData;
import com.birujung.memoride_frontend.request.BaseAPIService;
import com.birujung.memoride_frontend.request.BaseAPIUtils;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText emailInput;
    EditText passwordInput;
    Button loginButton;
    TextView registerLink;
    ProgressDialog loading;

    Context mContext;
    BaseAPIService mApiService;

    private AuthContext authContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        mApiService = BaseAPIUtils.getAPIService();

        authContext = new AuthContext(this);
        initComponents();
    }

    private void initComponents() {
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        registerLink = findViewById(R.id.register_link);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Loading...", true, false);
                requestLogin(emailInput, passwordInput);
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
    }

    private void requestLogin(EditText email, EditText password) {
        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();

        LoginData loginData = new LoginData(emailValue, passwordValue);

        Call<ResponseBody> call = mApiService.login(loginData);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    try{
                        String responseBody = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONObject userDataJson = jsonObject.getJSONObject("data");
                        UserData userData = new UserData();
                        userData.setUsername(userDataJson.getString("username"));
                        userData.setEmail(userDataJson.getString("email"));
                        userData.setRole(jsonObject.getString("role"));
                        userData.setFullName(userDataJson.getString("full_name"));
                        userData.setGender(userDataJson.getString("gender"));
                        userData.setPhoneNumber(userDataJson.getString("phone_num"));
                        userData.setMembershipLevel(userDataJson.getString("membership_level"));

                        // Store user data in SharedPreferences
                        authContext.setUser(userData);
                        Log.d("check","Check: " + userData);

                        Toast.makeText(mContext, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        });
    }
}
