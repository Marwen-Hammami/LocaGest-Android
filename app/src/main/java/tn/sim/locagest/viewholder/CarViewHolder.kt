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


                // Créer un Intent pour passer les données à DetailVoitureActivity
                val intent = Intent(context, detail_voiture::class.java)
                intent.putExtra("IMMATRICULATION", car.immatriculation)
                intent.putExtra("MARQUE", car.marque)
                intent.putExtra("MODELE", car.modele)
                intent.putExtra("BOITE", car.boite)
                intent.putExtra("CARBURANT", car.carburant)
                intent.putExtra("STATUT", car.disponibility)

                // Démarrer l'activité DetailVoitureActivity avec l'Intent
                context.startActivity(intent)

        }
    }

}
