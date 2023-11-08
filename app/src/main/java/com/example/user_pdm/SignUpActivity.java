package com.example.user_pdm;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // TODO: Add your sign-up logic here

        Button signUpButton = findViewById(R.id.signUpButton);
        // Set click listener for sign-up button and implement your logic

    }
}