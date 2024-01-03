package tn.sim.locagest.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.sim.locagest.R
import tn.sim.locagest.models.Agence
import tn.sim.locagest.models.User
import tn.sim.locagest.viewmodel.AgenceViewModel

class ManageAgenceActivity : AppCompatActivity() {

    private lateinit var topTextView: TextView
    private lateinit var editTextNom: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextLong: EditText
    private lateinit var editTextLat: EditText

    private lateinit var agenceViewModel: AgenceViewModel;
    private var agence: Agence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_agence)

        agenceViewModel = AgenceViewModel()

        initializeViews()

        val buttonAddOrUpdateAgence: Button = findViewById(R.id.buttonManageAgence)
        agence = intent?.getSerializableExtra("agence") as? Agence

        topTextView = findViewById(R.id.topTextView)

        if (agence != null) {
            editTextNom.setText(agence?.name)
            editTextAddress.setText(agence?.address)
            editTextLong.setText(agence?.longitude?.toString())
            editTextLat.setText(agence?.latitude?.toString())

            topTextView.text = "Update agence"
            buttonAddOrUpdateAgence.text = "Update"
        }

        buttonAddOrUpdateAgence.setOnClickListener {
            val name = editTextNom.text.toString()
            val address = editTextAddress.text.toString()
            val long = editTextLong.text.toString().toDoubleOrNull()
            val lat = editTextLat.text.toString().toDoubleOrNull()

            if (name.isEmpty() || address.isEmpty() || long == null || lat == null) {
                showToast("Please fill in all fields.")
                return@setOnClickListener
            }

            if (agence != null) {
                agence!!.name = name
                agence!!.address = address
                agence!!.longitude = long
                agence!!.latitude = lat

                agenceViewModel.update(agence!!.id!!, agence!!)
                agenceViewModel.updateLiveData.observe(this) {
                    finish()
                }
            } else {
                agenceViewModel.create(
                    Agence(
                        null,
                        name,
                        address,
                        User.currentUser,
                        long,
                        lat,
                    )
                )
                agenceViewModel.createLiveData.observe(this) {
                    finish()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initializeViews() {
        editTextNom = findViewById(R.id.editTextNom)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextLong = findViewById(R.id.editTextLong)
        editTextLat = findViewById(R.id.editTextLat)
    }
}