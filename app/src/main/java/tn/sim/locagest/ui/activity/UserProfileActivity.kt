package tn.sim.locagest.ui.activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import tn.sim.locagest.R
import tn.sim.locagest.models.User
import tn.sim.locagest.viewmodel.UserViewModel

class UserProfileActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences

    // Example UI elements
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView

    // Add more UI elements as needed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Get the user ID from SharedPreferences
        val userId = sharedPreferences.getString("userId", "") // Replace with your key
        if (userId != null && userId.isNotEmpty()) {
            // Fetch the user by ID
            userViewModel.getUserById(userId)
            val editButton = findViewById<Button>(R.id.editButton)
            editButton.setOnClickListener {
                // Handle the button click event
                val intent = Intent(this@UserProfileActivity, tn.sim.locagest.ui.activity.ProfileActivity::class.java)
                startActivity(intent)
            }
            // Check if night mode is enabled
            val nightModeSwitch = findViewById<Switch>(R.id.nightModeSwitch)
            val nightMode = AppCompatDelegate.getDefaultNightMode()

            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                nightModeSwitch.isChecked = true
            }

// Set the listener for the night mode switch
            nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                recreate()
            }

            // Observe the LiveData for the user by ID
            userViewModel.userById.observe(this) { user ->
                // Update UI with the user information
                if (user != null) {
                    // User found, update UI
                    updateUI(user)
                } else {
                    // Handle case where user is null or not found
                    showError()
                }
            }
        } else {
            // Handle case where user ID is not available in SharedPreferences
            showError()
        }
    }


    // Function to update the UI with user information
    private fun updateUI(user: User) {
        // Update UI elements with user information
        usernameTextView = findViewById(R.id.usernameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        firstNameTextView = findViewById(R.id.firstNameTextView)
        lastNameTextView = findViewById(R.id.lastNameTextView)

        usernameTextView.text = user.username
        emailTextView.text = user.email
        firstNameTextView.text = user.firstName
        lastNameTextView.text = user.lastName

        // Add more code to update other UI elements as needed
    }

    // Function to handle error when user is not found
    private fun showError() {
        // Handle error scenario
    }
}