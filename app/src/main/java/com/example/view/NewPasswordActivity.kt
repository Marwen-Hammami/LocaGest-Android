package com.example.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewPasswordActivity : AppCompatActivity() {
  /*  private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_password)

        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(
                    this@NewPasswordActivity,
                    "Please enter both password fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password != confirmPassword) {
                Toast.makeText(
                    this@NewPasswordActivity,
                    "Passwords do not match",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Passwords match, perform save logic here
                // You can save the new password to your database or perform any desired action
                Toast.makeText(
                    this@NewPasswordActivity,
                    "Password saved successfully",
                    Toast.LENGTH_SHORT
                ).show()

                // Finish the activity or navigate to the next screen
                finish()
            }
        }
    }*/
}