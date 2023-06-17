package com.birujung.memoride_frontend;
import com.birujung.memoride_frontend.helper.AuthContext;
import com.birujung.memoride_frontend.helper.LoginData;
import com.birujung.memoride_frontend.model.UserData;
import com.birujung.memoride_frontend.request.BaseAPIService;
import com.birujung.memoride_frontend.request.BaseAPIUtils;
import com.birujung.memoride_frontend.helper.TokenResponse;

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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
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
                requestLogin();
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
            }
        });
    }

    private void requestLogin() {
        LoginData loginData = new LoginData(emailInput.getText().toString(), passwordInput.getText().toString());

        mApiService.login(loginData)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            // Handle the successful login response

                            // Set the user information in the AuthContext
                            String username = emailInput.getText().toString();
                            authContext.setUser(username);

                            Toast.makeText(mContext, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, MainActivity.class);
                            startActivity(intent);
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
