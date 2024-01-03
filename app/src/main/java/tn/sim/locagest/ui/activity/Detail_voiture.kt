package tn.sim.locagest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import tn.sim.locagest.databinding.DetailVoitureBinding
import tn.sim.locagest.models.Car

class detail_voiture : AppCompatActivity() {

    private lateinit var binding: DetailVoitureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailVoitureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Retourne.setOnClickListener {
            onBackPressed()
        }

        // Récupérer les données passées depuis l'intent
        val immatriculation = intent.getStringExtra("IMMATRICULATION")
        val marque = intent.getStringExtra("MARQUE")
        val modele = intent.getStringExtra("MODELE")
        val disponibility = intent.getStringExtra("STATUT")
        val boite = intent.getStringExtra("BOITE")
        val carburant = intent.getStringExtra("CARBURANT")

        // Afficher les données dans votre interface utilisateur (TextView, etc.)
        binding.textViewImmatriculation.text = "Immatriculation : $immatriculation"
        binding.textViewMarque.text = "Marque : $marque"
        binding.textViewModele.text = "Modèle : $modele"
        binding.textViewStatut.text = "Statut : $disponibility"
        binding.textViewBoite.text = "Boîte : $boite"
        binding.textViewCarburant.text = "Carburant : $carburant"

        binding.btnModifier.setOnClickListener {
            // Redirection vers l'interface modifier_voiture
            val car = Car(
                immatriculation = immatriculation ?: "",
                marque = marque ?: "",
                modele = modele ?: "",
                disponibility = disponibility ?: "",
                boite = boite ?: "",
                carburant = carburant ?: ""
            )
            val intent = Intent(this@detail_voiture, modifier_voiture::class.java)
            // on cliquant sur se bouton je veux envoyer tout les information a cett intent
            intent.putExtra("car", car)
            startActivity(intent)
        }


    }
    override fun onBackPressed() {


        // Appeler la méthode super.onBackPressed() pour fermer l'activité actuelle
        super.onBackPressed()
    }
}
