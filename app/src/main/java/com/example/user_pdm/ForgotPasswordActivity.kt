package com.example.user_pdm
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var resetPasswordByEmailButton: Button
    private lateinit var resetPasswordBySMSButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forget_password)

        emailEditText = findViewById(R.id.emailEditText)
        resetPasswordByEmailButton = findViewById(R.id.resetPasswordByEmailButton)
        resetPasswordBySMSButton = findViewById(R.id.resetPasswordBySMSButton)
        cancelButton = findViewById(R.id.cancelButton)

        resetPasswordByEmailButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            // TODO: Implement password reset by email logic
            Toast.makeText(this, "Reset password by email: $email", Toast.LENGTH_SHORT).show()
        }

        resetPasswordBySMSButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            // TODO: Implement password reset by SMS logic
            Toast.makeText(this, "Reset password by SMS: $email", Toast.LENGTH_SHORT).show()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}