package tn.sim.locagest.ui.activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import tn.sim.locagest.R
import tn.sim.locagest.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var emailEditText: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.forgot_password)

        emailEditText = findViewById(R.id.emailTextInputLayout)
//        val resetPasswordByEmailButton: Button = findViewById(R.id.resetPasswordByEmailButton)
//        val resetPasswordBySMSButton: Button = findViewById(R.id.resetPasswordBySMSButton)
//        val cancelButton: Button = findViewById(R.id.cancelButton)

        userViewModel = UserViewModel()

//        resetPasswordByEmailButton.setOnClickListener {
//            val email = emailEditText.editText?.text.toString()
//
//            // Log the email before making the request
//            Log.d("ForgotPasswordActivity", "Email: $email")
//
//            if (email.isNotEmpty()) {
//                userViewModel.forgotPassword(email).observe(this, Observer { message ->
//                    if (!message.isNullOrBlank()) { // Check if message is not null or blank
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//
//                        // Log the success message
//                        Log.d("ForgotPasswordActivity", "Reset Password Successful: $message")
//
//                        // Start the NewPasswordActivity after a successful password reset
//                        val intent = Intent(this, NewPasswordActivity::class.java)
//                        startActivity(intent)
//
//                        // Finish the current activity to prevent going back to ForgotPasswordActivity
//                        finish()
//                    } else {
//                        // Log the error message if any
//                        Log.e("ForgotPasswordActivity", "Reset Password Failed")
//                    }
//                })
//            } else {
//                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//        // Handle the click event for the other buttons here
//        resetPasswordBySMSButton.setOnClickListener {
//            val email = emailEditText.editText?.text.toString()
//
//            // Log the email before making the request
//            Log.d("ForgotPasswordActivity", "Email: $email")
//
//            if (email.isNotEmpty()) {
//                userViewModel.forgotPasswordSMS(email).observe(this, Observer { message ->
//                    if (!message.isNullOrBlank()) { // Check if message is not null or blank
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//
//                        // Log the success message
//                        Log.d("ForgotPasswordActivity", "Reset Password Successful: $message")
//
//                        // Start the NewPasswordActivity after a successful password reset
//                        val intent = Intent(this, NewPasswordActivity::class.java)
//                        startActivity(intent)
//
//                        // Finish the current activity to prevent going back to ForgotPasswordActivity
//                        finish()
//                    } else {
//                        // Log the error message if any
//                        Log.e("ForgotPasswordActivity", "Reset Password Failed")
//                    }
//                })
//            } else {
//                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//
//        cancelButton.setOnClickListener {
//            finish()
//        }
    }
}
