package com.example.user_pdm;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // TODO: Add your login logic here

        Button loginButton = findViewById(R.id.loginButton);
        // Set click listener for login button and implement your logic

    }
}