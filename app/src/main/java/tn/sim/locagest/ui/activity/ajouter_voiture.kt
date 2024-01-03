package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.sim.locagest.databinding.AjouterVoitureBinding
import android.widget.Toast
import java.util.regex.Pattern
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.api.FlotteService
import tn.sim.locagest.api.retrofit.RetroInstance
import tn.sim.locagest.models.Car

class ajouter_voiture : AppCompatActivity() {

    private lateinit var binding: AjouterVoitureBinding

    // Assuming you have a Retrofit instance set up
    private val carService: FlotteService by lazy {
        RetroInstance.getRetroInstance().create(FlotteService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AjouterVoitureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Return.setOnClickListener {
            onBackPressed()
        }

        binding.btnAddCar.setOnClickListener {
            val immatriculation = binding.ImmatriculationText.text.toString()
            val marque = binding.editTextMarque.text.toString()
            val modele = binding.editTextModele.text.toString()
            val carburant = binding.editTextCarburant.text.toString()
            val boite = binding.editTextTransmission.text.toString()

            if (immatriculation.isNotEmpty() && marque.isNotEmpty() && modele.isNotEmpty() && carburant.isNotEmpty() && boite.isNotEmpty()) {
                if (isValidImmatriculationFormat(immatriculation)) {
                    val car = Car(immatriculation, marque, modele, carburant, boite, disponibility = "Disponible")
                    println(car)


                    carService.createCar(car).enqueue(object : Callback<Car> {
                        override fun onResponse(call: Call<Car>, response: Response<Car>) {
                            response.takeIf { it.isSuccessful }?.body()?.let {
                                Toast.makeText(this@ajouter_voiture, "Voiture ajoutée avec succès", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@ajouter_voiture, detail_flotte::class.java))
                            } ?: Toast.makeText(this@ajouter_voiture, "Erreur lors de l'ajout de la voiture", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(call: Call<Car>, t: Throwable) {
                            Toast.makeText(this@ajouter_voiture, "Erreur réseau", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(this@ajouter_voiture, "Format d'immatriculation incorrect", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@ajouter_voiture, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Move the function outside of onCreate
    private fun isValidImmatriculationFormat(immatriculation: String): Boolean {
        val pattern = Pattern.compile("\\d{1,4}TU\\d{1,3}")
        return pattern.matcher(immatriculation).matches()
    }
    override fun onBackPressed() {


        // Appeler la méthode super.onBackPressed() pour fermer l'activité actuelle
        super.onBackPressed()
    }
}
