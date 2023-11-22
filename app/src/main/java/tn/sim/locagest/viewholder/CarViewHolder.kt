package tn.sim.locagest.viewholder

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardviewVoitureBinding
import tn.sim.locagest.models.Car
import tn.sim.locagest.ui.activity.detail_voiture


    class CarViewHolder(val context: Context, val binding: CardviewVoitureBinding) : RecyclerView.ViewHolder(binding.root) {

            // Populate the views with data from the Car object
    fun setData(car: Car ) {
        binding.titre.text = "${car.marque} ${car.modele}"
        binding.matricule.text = car.immatriculation
        binding.disponibility.text = car.disponibility
        binding.buttonDetails.setOnClickListener {

                // Créer une instance de la classe Car avec des données spécifiques
                val voiture = Car("2578 TU 237", "Dacia", "Logan", "Essence", "Manuelle","Disponible")

                // Créer un Intent pour passer les données à DetailVoitureActivity
                val intent = Intent(context, detail_voiture::class.java)
                intent.putExtra("IMMATRICULATION", voiture.immatriculation)
                intent.putExtra("MARQUE", voiture.marque)
                intent.putExtra("MODELE", voiture.modele)
                intent.putExtra("BOITE", voiture.boite)
                intent.putExtra("CARBURANT", voiture.carburant)

                // Démarrer l'activité DetailVoitureActivity avec l'Intent
                context.startActivity(intent)

        }
    }

}
