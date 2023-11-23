package tn.sim.locagest.ui.activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerificationMailMobileActivity : AppCompatActivity() {
   /* private lateinit var codeEditText: EditText
    private lateinit var verifyButton: Button
    private lateinit var resendMessageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verif_mobile_email)

        codeEditText = findViewById(R.id.codeEditText)
        verifyButton = findViewById(R.id.verifyButton)
        resendMessageTextView = findViewById(R.id.resendMessageTextView)

        verifyButton.setOnClickListener {
            val verificationCode = codeEditText.text.toString()
            if (verificationCode.isEmpty()) {
                Toast.makeText(
                    this@VerificationMailMobileActivity,
                    "Please enter the verification code",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                if (verificationCode == "1234") {
                    Toast.makeText(
                        this@VerificationMailMobileActivity,
                        "Verification successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@VerificationMailMobileActivity,
                        "Invalid verification code",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        resendMessageTextView.setOnClickListener {
            Toast.makeText(
                this@VerificationMailMobileActivity,
                "Resend message clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }*/
}