package tn.sim.locagest.ui.activity

import android.content.Context
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.textfield.TextInputLayout
import tn.sim.locagest.R
import tn.sim.locagest.viewmodel.UserViewModel

class SignInActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var rememberMeCheckbox: CheckBox
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var callbackManager: CallbackManager
    private lateinit var googleApiClient: GoogleApiClient

    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        callbackManager = CallbackManager.Factory.create()

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        // Initialize Facebook
        FacebookSdk.sdkInitialize(applicationContext)

        emailTextInputLayout = findViewById(R.id.emailTextInputLayout)
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        if (rememberMeCheckbox.isChecked) {
            emailTextInputLayout.editText?.setText(sharedPreferences.getString("email", ""))
            passwordTextInputLayout.editText?.setText(sharedPreferences.getString("password", ""))
        }

        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            val email: String = emailTextInputLayout.editText?.text.toString()
            val password: String = passwordTextInputLayout.editText?.text.toString()
            if (rememberMeCheckbox.isChecked) {
                with(sharedPreferences.edit()) {
                    putString("email", email)
                    putString("password", password)
                    apply()
                }
            }

            Log.d("SIGN_IN_BUTTON", "Email: $email, Password: $password")
            viewModel.signInUser(email, password)

            viewModel.signInResult.observe(this, Observer { result ->
                Log.d("SIGN_IN_OBSERVER", "Observer triggered")
                if (result.success) {
                    val sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(
                        "userId",
                        result.user?._id
                    ) // Replace userId with your actual user ID
                    editor.apply()

                    val userToken = result.token
                    val userId = result.user?._id

                    Log.d("SIGN_IN_OBSERVER", "User Token: $userToken")
                    Log.d("SIGN_IN_OBSERVER", "User ID: $userId")

                    // Navigate to ProfileActivity and pass the user token and user ID
                    val intent = Intent(this@SignInActivity, UserProfile::class.java).apply {
                        putExtra("userToken", userToken)
                        putExtra("userId", userId)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    // Handle sign-in error
                }
            })
        }

        val signUpTextView: TextView = findViewById(R.id.signUpTextView)
        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val forgotPasswordTextView: TextView = findViewById(R.id.forgotPasswordTextView)
        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Initialize Facebook login
        callbackManager = CallbackManager.Factory.create()

        val facebookLoginImageView = findViewById<ImageView>(R.id.facebookLoginButton)
        facebookLoginImageView.setOnClickListener {
            // Launch Facebook login dialog
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("email", "public_profile"))
        }

        // Handle Facebook login result
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // Handle successful login
                    val accessToken = loginResult.accessToken
                    val userId = accessToken.userId // Get the user ID

                    // Store the user ID in SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("userId", userId)
                    editor.apply()

                    Log.d("FACEBOOK_LOGIN", "Facebook login successful, UserID: $userId ")
                    // Navigate to ProfileActivity
                    val intent = Intent(this@SignInActivity, UserProfile::class.java)
                    intent.putExtra("userToken", accessToken.token)
                    startActivity(intent)
                    // finish() // Optional: Finish the current activity if needed
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

        // Initialize Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this) { connectionResult ->
                // Handle GoogleApiClient connection failure
                Log.e(
                    "GOOGLE_SIGN_IN",
                    "GoogleApiClient connection failed: ${connectionResult.errorMessage}"
                )
            }
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        val googleSignInButton = findViewById<ImageView>(R.id.googleLogo)
        googleSignInButton.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            if (result != null) {
                handleGoogleSignInResult(result)
            }
        }
    }

    private fun handleGoogleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val account = result.signInAccount
            val userId = account?.id // Get the user ID

            // Store the user ID in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("userId", userId)
            editor.apply()

            Log.d("GOOGLE_SIGN_IN", "Google sign-in successful, UserID: $userId")

            // Navigate to ProfileActivity
            val intent = Intent(this@SignInActivity, ProfileActivity::class.java)
            intent.putExtra("userToken", account?.idToken)
            startActivity(intent)
            //finish() // Optional: Finish the current activity if needed
        } else {
            // Handle Google sign-in failure
            Log.e("GOOGLE_SIGN_IN", "Google sign-in failed: ${result.status.statusMessage}")
        }
    }
}