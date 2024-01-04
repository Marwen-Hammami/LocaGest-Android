

package tn.sim.locagest.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import tn.sim.locagest.MainActivityFlotte
import tn.sim.locagest.MainActivityReservation
import tn.sim.locagest.R
import tn.sim.locagest.models.User
import tn.sim.locagest.ui.MainActivityChat
import tn.sim.locagest.viewmodel.UserViewModel

class UserProfile: AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var logoutButton: Button
    private lateinit var deleteAccountButton: Button

    // UI components
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var nightModeSwitch: Switch
    private lateinit var editButton: Button
    // Add other UI components as needed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize UI components
        logoutButton = findViewById(R.id.logoutButton)
        deleteAccountButton = findViewById(R.id.deleteAccountButton)

        // Set Click Listeners
        logoutButton.setOnClickListener {
            performLogout()
        }

        deleteAccountButton.setOnClickListener {
            performDeleteAccount()
        }





        emailTextView = findViewById(R.id.EmailTextView)
        nightModeSwitch = findViewById(R.id.darkModeSwitch)
        editButton = findViewById(R.id.editButton)

        // Initialize ViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Get the user ID from SharedPreferences
        sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "")
        if (userId != null && userId.isNotEmpty()) {
            fetchUserData(userId)
        } else {
            // Handle case where user ID is not available
            showError()
        }

        // Night Mode Switch Listener
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }

        // Edit Profile Button Listener
        editButton.setOnClickListener {
            // Handle the Edit Profile action
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
    private fun performLogout() {
        clearUserPreferences()
        navigateToSignInActivity()
    }

    private fun performDeleteAccount() {
        // You can add additional logic here if needed
        clearUserPreferences()
        navigateToSignInActivity()
    }

    private fun clearUserPreferences() {
        // Clear user data from SharedPreferences
        val editor = sharedPreferences.edit()
        editor.remove("userId")
        editor.apply() // or editor.commit() if you need synchronous operation
    }

    private fun navigateToSignInActivity() {
        // Navigate to SignInActivity
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish() // Close the current activity
    }

    // Activity
    private fun fetchUserData(userId: String) {
        userViewModel.userById.observe(this) { user ->
            if (user != null) {
                updateUI(user)
            } else {
                showError()
            }
        }


}

    private fun updateUI(user: User) {
        usernameTextView.text = user.username
        emailTextView.text = user.email
        // Update other UI components as needed
    }

    private fun showError() {
        // Handle error case
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

    // Override other methods as needed, such as onCreateOptionsMenu and onOptionsItemSelected
}
