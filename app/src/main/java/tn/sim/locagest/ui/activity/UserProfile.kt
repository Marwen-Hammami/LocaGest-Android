package tn.sim.locagest.ui.activity

import android.R
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import tn.sim.locagest.models.User
import tn.sim.locagest.viewmodel.UserViewModel
import androidx.appcompat.widget.Toolbar
import tn.sim.locagest.MainActivityFlotte
import tn.sim.locagest.MainActivityReservation
import tn.sim.locagest.ui.MainActivityChat

class UserProfile: AppCompatActivity() {
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
        setContentView(tn.sim.locagest.R.layout.user_profile)


        val toolbar: Toolbar = findViewById(tn.sim.locagest.R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Get the user ID from SharedPreferences
        val userId = sharedPreferences.getString("userId", "") // Replace with your key
        if (userId != null && userId.isNotEmpty()) {
            // Fetch the user by ID
            userViewModel.getUserById(userId)
            val editButton = findViewById<Button>(tn.sim.locagest.R.id.editButton)
            editButton.setOnClickListener {
                // Handle the button click event
                val intent = Intent(this@UserProfile,ProfileActivity::class.java)
                startActivity(intent)
            }
            // Check if night mode is enabled
            val nightModeSwitch = findViewById<Switch>(tn.sim.locagest.R.id.nightModeSwitch)
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
        usernameTextView = findViewById(tn.sim.locagest.R.id.usernameTextView)
        emailTextView = findViewById(tn.sim.locagest.R.id.emailTextView)
        firstNameTextView = findViewById(tn.sim.locagest.R.id.firstNameTextView)
        lastNameTextView = findViewById(tn.sim.locagest.R.id.lastNameTextView)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(tn.sim.locagest.R.menu.btm_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        return when (item.itemId) {
            tn.sim.locagest.R.id.CarPage -> {
                intent = Intent(this, MainActivityFlotte::class.java)
                startActivity(intent)
                true
            }

            tn.sim.locagest.R.id.UserPage -> {
                intent = Intent(this, UserProfile::class.java)
                startActivity(intent)
                true
            }
            tn.sim.locagest.R.id.ChatPage-> {
                intent = Intent(this, MainActivityChat::class.java)
                startActivity(intent)
                true
            }
            tn.sim.locagest.R.id.ResPage-> {
                intent = Intent(this, MainActivityReservation::class.java)
                startActivity(intent)
                true
            }
            tn.sim.locagest.R.id.AgencePage-> {
                intent = Intent(this, AgenceListActivity::class.java)
                startActivity(intent)
                true
            }
           /* tn.sim.locagest.R.id.GaragePage-> {
                intent = Intent(this, ::class.java)
                startActivity(intent)
                true
            }*/

            else -> super.onOptionsItemSelected(item)
        }
    }


}