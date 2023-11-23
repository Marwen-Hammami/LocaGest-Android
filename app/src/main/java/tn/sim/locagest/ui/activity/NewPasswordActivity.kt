package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tn.sim.locagest.R
import tn.sim.locagest.viewmodel.UserViewModel

class NewPasswordActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var otpEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_password)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        otpEditText = findViewById(R.id.passwordEditText)
        newPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            savePassword()
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        // Observe the resetPasswordResponse LiveData
        userViewModel.resetPasswordResponse.observe(this, Observer { response ->
            // Handle the response here
            if (response != null) {
                if (response.error != null) {
                    // Handle error case
                    Toast.makeText(this, "Password reset failed: ${response.error}", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle success case
                    Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show()
                    // Optionally, you can navigate to the next screen or finish the activity
                    // finish()
                }
            }
        })
    }

    private fun savePassword() {
        val otpCode = otpEditText.text.toString().trim()
        val newPassword = newPasswordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        if (otpCode.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Call the resetPassword function from the ViewModel
        val email = "maher.karoui@esprit.tn" // replace with the actual email
        userViewModel.resetPassword(email, otpCode, newPassword)
    }
}
