package tn.sim.locagest.viewholder

import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardviewEntretienBinding
import tn.sim.locagest.models.HistoriqueEntretien

class HistoriqueEntretienViewHolder(val binding: CardviewEntretienBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(historiqueEntretien: HistoriqueEntretien) {
        binding.titre.text = historiqueEntretien.description ?: ""
        binding.matricule.text = historiqueEntretien.immatriculation ?: ""
        binding.disponibility.text = historiqueEntretien.vehicule_type ?: ""

        // Ajoutez ici d'autres vues ou attributs de binding pour les autres champs
    }
}