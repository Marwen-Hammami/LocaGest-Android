package tn.sim.locagest.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import tn.sim.locagest.R
import tn.sim.locagest.viewmodel.UserViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var rememberMeCheckbox: CheckBox
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initialize Facebook SDK
        FacebookSdk.sdkInitialize(applicationContext)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Find your views...
        emailTextInputLayout = findViewById(R.id.emailTextInputLayout)
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout)
        val signInButton: Button = findViewById(R.id.signInButton)
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox)

        // Initialize SharedPreferences
        sharedPreferences = getPreferences(MODE_PRIVATE)

        // Check if Remember Me is checked and populate email/password if available
        if (rememberMeCheckbox.isChecked) {
            emailTextInputLayout.editText?.setText(sharedPreferences.getString("email", ""))
            passwordTextInputLayout.editText?.setText(sharedPreferences.getString("password", ""))
        }

        signInButton.setOnClickListener {
            val emailEditText: EditText = emailTextInputLayout.editText!!
            val passwordEditText: EditText = passwordTextInputLayout.editText!!

            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Save credentials if Remember Me is checked
            if (rememberMeCheckbox.isChecked) {
                with(sharedPreferences.edit()) {
                    putString("email", email)
                    putString("password", password)
                    apply()
                }
            }

            viewModel.signInUser(email, password)

        }

        viewModel.signedInUser.observe(this) { user ->
            if (user != null) {
//                val intent = Intent(this, ProfileActivity::class.java).apply {
//                    putExtra("userId", user.id ?: "")
//                }
                startActivity(intent)
                // Handle successful sign-in
                Log.d("SIGN_IN_BUTTON", "User signed in successfully: $user")
                Log.d("SIGN_IN_BUTTON", "Starting ProfileActivity")


            } else {
                // Handle sign-in failure
                Log.d("SIGN_IN_BUTTON", "Sign-in failed")
            }
        }


        val signUpTextView: TextView = findViewById(R.id.signUpTextView)

        // Set a click listener on the TextView
        signUpTextView.setOnClickListener {
            // Handle the click event, navigate to SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        val forgotPasswordTextView: TextView = findViewById(R.id.forgotPasswordTextView)

        forgotPasswordTextView.setOnClickListener {
            // Handle the click event, navigate to ForgotPasswordActivity or show a dialog
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }


        // Initialize Facebook login
        callbackManager = CallbackManager.Factory.create()

        val facebookLoginImageView = findViewById<ImageView>(R.id.facebookLoginButton)
        facebookLoginImageView.setOnClickListener {
            // Launch Facebook login dialog
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))
        }

        // Handle Facebook login result
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // Handle successful login
                val accessToken = loginResult.accessToken
                Log.d("FACEBOOK_LOGIN", "Facebook login successful, AccessToken: $accessToken")

            }

            override fun onCancel() {
                // Handle canceled login
                Log.d("FACEBOOK_LOGIN", "Facebook login canceled")
            }

            override fun onError(error: FacebookException) {
                // Handle error in login
                Log.e("FACEBOOK_LOGIN", "Facebook login error: ${error.message}", error)
            }
        })
    }

    // Add the onActivityResult method for Facebook login
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
