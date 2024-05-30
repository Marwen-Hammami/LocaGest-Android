package tn.sim.locagest.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import tn.sim.locagest.R
import tn.sim.locagest.models.User

import tn.sim.locagest.viewmodel.UserViewModel

class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    private lateinit var profilePicture: ImageView
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var newPassword: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var creditCardNumber: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val id = getStoredUserId() // Retrieve the user ID from SharedPreferences

        // Initialize your views
        profilePicture = findViewById(R.id.profilePicture)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        newPassword = findViewById(R.id.newPassword)
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        creditCardNumber = findViewById(R.id.creditCardNumber)
        updateButton = findViewById(R.id.btnUpdate)

        updateButton.setOnClickListener {
            if (id == null) {
                Log.e("Debug", "No user ID found")
                return@setOnClickListener
            }

            Log.d("Debug", "Updating user with ID: $id")
            val user = User(
                _id = id,
                username = username.text.toString(),
                email = email.text.toString(),
                password = newPassword.text.toString(),
                firstName = firstName.text.toString(),
                lastName = lastName.text.toString(),
                creditCardNumber = creditCardNumber.text.toString(),
                rate = "GOOD",
                specialization = null,
                experience = null,
                roles = "client",
                token = null
            )
            viewModel.updateUserProfile(id, user)
        }
    }

    private fun getStoredUserId(): String? {
        val sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", null)
    }
}