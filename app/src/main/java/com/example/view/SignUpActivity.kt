package com.example.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.model.User
import com.example.user_pdm.R
import com.example.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Use TextInputLayout instead of EditText for your views
        val usernameTextInputLayout: TextInputLayout = findViewById(R.id.usernameTextInputLayout)
        val emailTextInputLayout: TextInputLayout = findViewById(R.id.emailTextInputLayout)
        val passwordTextInputLayout: TextInputLayout = findViewById(R.id.passwordTextInputLayout)
        val signUpButton: Button = findViewById(R.id.signUpButton)

        viewModel.createdUser.observe(this) { user ->
            if (user != null) {
                // Handle the created user
                Log.d("SIGN_UP_BUTTON", "User created successfully: $user")
            } else {
                // Handle the error
                Log.d("SIGN_UP_BUTTON", "User creation failed")
            }
        }

        signUpButton.setOnClickListener {
            val username = usernameTextInputLayout.editText?.text.toString()
            val email = emailTextInputLayout.editText?.text.toString()
            val password = passwordTextInputLayout.editText?.text.toString()

            val user = User(username, email, password, null, null, null, "GOOD", null, null)
            viewModel.createUser(user)
        }
    }
}



