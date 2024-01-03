package tn.sim.locagest.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardviewEntretienBinding
import tn.sim.locagest.models.HistoriqueEntretien
import tn.sim.locagest.viewholder.HistoriqueEntretienViewHolder

class HistoriqueEntretienAdapter(val historiqueEntretiens: Array<HistoriqueEntretien>) :
    RecyclerView.Adapter<HistoriqueEntretienViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriqueEntretienViewHolder {
        val binding = CardviewEntretienBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return HistoriqueEntretienViewHolder(binding)
    }

    override fun getItemCount(): Int = historiqueEntretiens.size

    override fun onBindViewHolder(holder: HistoriqueEntretienViewHolder, position: Int) {
        val historiqueEntretien = historiqueEntretiens[position]
        holder.setData(historiqueEntretien)
    }
}
