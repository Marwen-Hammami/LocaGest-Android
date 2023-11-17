// SignUpActivity
package com.example.view
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.model.User
import com.example.user_pdm.R
import com.example.viewmodel.UserViewModel
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

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

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
                    putExtra("userId", user.id)
                }
                startActivity(intent)
            } else {
                Log.d("SIGN_UP_BUTTON", "User creation failed")
            }
        }

        signUpButton.setOnClickListener {
            val username = usernameTextInputLayout.editText?.text.toString()
            val email = emailTextInputLayout.editText?.text.toString()
            val password = passwordTextInputLayout.editText?.text.toString()

            val user = User(null, username, email, password, null, null, null, "GOOD", null, null)
            viewModel.createUser(user)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            if (result != null) {
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

            val user = User(null, username, email, password, null, null, null, "GOOD", null, null)
            viewModel.createUser(user)

            viewModel.createdUser.observe(this) { createdUser ->
                if (createdUser != null) {
                    Log.d("SIGN_UP_BUTTON", "User created successfully: $createdUser")
                    val intent = Intent(this, ProfileActivity::class.java).apply {
                        putExtra("userId", createdUser.id)
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
