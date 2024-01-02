package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.databinding.ModifierVoitureBinding
import tn.sim.locagest.models.Car
import tn.sim.locagest.api.FlotteService
import tn.sim.locagest.api.retrofit.RetroInstance

class modifier_voiture : AppCompatActivity() {

    private lateinit var binding: ModifierVoitureBinding
    private val carService: FlotteService by lazy {
        RetroInstance.getRetroInstance().create(FlotteService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifierVoitureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            val immatriculation = binding.imm.editText?.text.toString()
            val marque = binding.marque.editText?.text.toString()
            val modele = binding.modele.editText?.text.toString()
            val carburant = binding.carburant.editText?.text.toString()
            val boite = binding.boite.editText?.text.toString()

            if (immatriculation.isNotEmpty() && marque.isNotEmpty() && modele.isNotEmpty() && carburant.isNotEmpty() && boite.isNotEmpty()) {
                if (true) { // You might want to replace this condition with the appropriate validation
                    val car = Car(immatriculation, marque, modele, carburant, boite, disponibility = "Disponible")

                    carService.updateCar(immatriculation, car).enqueue(object : Callback<Car> {
                        override fun onResponse(call: Call<Car>, response: Response<Car>) {
                            // Cacher l'indicateur de chargement
                            // Exemple : hideLoadingIndicator()

                            response.body()?.let {
                                Toast.makeText(this@modifier_voiture, "Voiture modifiée avec succès", Toast.LENGTH_SHORT).show()
                            } ?: run {
                                Toast.makeText(this@modifier_voiture, "Erreur lors de la modification de la voiture", Toast.LENGTH_SHORT).show()
                            }

                            // Navigate to the page_entretien activity
                            startActivity(Intent(this@modifier_voiture, detail_flotte::class.java))
                        }

                        override fun onFailure(call: Call<Car>, t: Throwable) {
                            // Cacher l'indicateur de chargement en cas d'échec
                            // Exemple : hideLoadingIndicator()

                            // Toast.makeText(this@modifier_voiture, "Erreur réseau", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(this@modifier_voiture, "Format d'immatriculation incorrect", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@modifier_voiture, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageView5.setOnClickListener {
            onBackPressed()
        }
    }

    private fun isValidImmatriculationFormat(immatriculation: String): Boolean {
        val regex = Regex("\\d{1,4}TU\\d{1,3}")
        return regex.matches(immatriculation)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
