// SignUpActivity
package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import tn.sim.locagest.models.User
import tn.sim.locagest.R
import tn.sim.locagest.viewmodel.UserViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val usernameTextInputLayout: TextInputLayout = findViewById(R.id.usernameTextInputLayout)
        val emailTextInputLayout: TextInputLayout = findViewById(R.id.emailTextInputLayout)
        val passwordTextInputLayout: TextInputLayout = findViewById(R.id.passwordTextInputLayout)
        val signUpButton: Button = findViewById(R.id.signUpButton)
        val googleSignInButton: ImageView = findViewById(R.id.googleLogo)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        viewModel.createdUser.observe(this) { user ->
            if (user != null) {
                Log.d("SIGN_UP_BUTTON", "User created successfully: $user")
                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtra("userId", user._id)
                }
                startActivity(intent)
            } else {
                Log.d("SIGN_UP_BUTTON", "User creation failed")
            }
        }

        callbackManager = CallbackManager.Factory.create()

        val facebookLoginButton: ImageView = findViewById(R.id.facebookLogo)
        facebookLoginButton.setOnClickListener {
            // Trigger Facebook login
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("email", "public_profile"))
        }

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    val accessToken = loginResult.accessToken
                    // Use the access token to make requests to the Facebook API
                    // You can retrieve user information from the accessToken if needed
                }

                override fun onCancel() {
                    // Handle canceled Facebook login
                }

                override fun onError(error: FacebookException) {
                    // Handle error in Facebook login
                }
            })

        signUpButton.setOnClickListener {
            val username = usernameTextInputLayout.editText?.text.toString()
            val email = emailTextInputLayout.editText?.text.toString()
            val password = passwordTextInputLayout.editText?.text.toString()

            val user = User(
                "",
                username,
                email,
                password,
                null,
                null,
                null,
                "GOOD",
                null,
                null,
                "client",
                null
            )
            viewModel.createUser(user)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            if (result != null && result.isSuccess) {
                handleSignInResult(result)
            }
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        val usernameTextInputLayout: TextInputLayout = findViewById(R.id.usernameTextInputLayout)
        val emailTextInputLayout: TextInputLayout = findViewById(R.id.emailTextInputLayout)
        val passwordTextInputLayout: TextInputLayout = findViewById(R.id.passwordTextInputLayout)

        if (result.isSuccess) {
            // Signed in successfully
            val account = result.signInAccount
            Log.d("GoogleSignIn", "account: $account") // Check GoogleSignInAccount details

            val username = account?.displayName ?: "default_username"
            val email = account?.email ?: "default_email"
            val password = account?.id ?: "default_password"

            // Log values to check if they are retrieved correctly
            Log.d("EditText", "username: $username")
            Log.d("EditText", "email: $email")
            Log.d("EditText", "password: $password")

            // Ensure that the EditText components are not null before attempting to set text
            usernameTextInputLayout.editText?.setText(username)
            emailTextInputLayout.editText?.setText(email)
            passwordTextInputLayout.editText?.setText(password)

            val user = User(
                "",
                username,
                email,
                password,
                null,
                null,
                null,
                "GOOD",
                null,
                null,
                "client",
                null
            )
            viewModel.createUser(user)

            viewModel.createdUser.observe(this) { createdUser ->
                if (createdUser != null) {
                    Log.d("SIGN_UP_BUTTON", "User created successfully: $createdUser")
                    val intent = Intent(this, ProfileActivity::class.java).apply {
                        putExtra("userId", createdUser._id)
                    }
                    startActivity(intent)
                } else {
                    Log.d("SIGN_UP_BUTTON", "User creation failed")
                }
            }
        } else {
            // Handle sign-in failure if needed
        }
    }
}

