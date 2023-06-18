package com.birujung.memoride_frontend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.birujung.memoride_frontend.model.UserData;
import com.birujung.memoride_frontend.request.BaseAPIService;
import com.birujung.memoride_frontend.request.BaseAPIUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameInput;
    EditText emailInput;
    EditText passwordInput;
    EditText photoInput;
    EditText roleInput;
    EditText fullNameInput;
    EditText genderInput;
    EditText phoneNumberInput;
    Button registerButton;

    Context mContext;
    BaseAPIService mApiService;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;
        mApiService = BaseAPIUtils.getAPIService();

        // Initialize EditText fields and Register button
        usernameInput = findViewById(R.id.username_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        photoInput = findViewById(R.id.photo_input);
        roleInput = findViewById(R.id.role_input);
        fullNameInput = findViewById(R.id.full_name_input);
        genderInput = findViewById(R.id.gender_input);
        phoneNumberInput = findViewById(R.id.phone_number_input);
        registerButton = findViewById(R.id.register_button);
        loginLink = findViewById(R.id.login_link);

        // Set a click listener for the Register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String photo = photoInput.getText().toString();
                String role = roleInput.getText().toString();
                String fullName = fullNameInput.getText().toString();
                String gender = genderInput.getText().toString();
                String phoneNumber = phoneNumberInput.getText().toString();

                // Create a new UserData object with the user input
                UserData userData = new UserData();
                userData.setUsername(username);
                userData.setEmail(email);
                userData.setPassword(password);
                userData.setPhoto(photo);
                userData.setRole(role);
                userData.setFullName(fullName);
                userData.setGender(gender);
                userData.setPhoneNumber(phoneNumber);

                // Make the register API call
                registerUser(userData);
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });
    }

    private void registerUser(UserData userData) {
        Call<ResponseBody> call = mApiService.register(userData);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Registration successful
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                } else {
                    // Registration failed
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                Toast.makeText(RegisterActivity.this, "Registration failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
