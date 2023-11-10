package tn.sim.locagest.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.databinding.ItemHistoriqueBinding
import java.text.SimpleDateFormat


class HistoriqueViewHolder(private val binding: ItemHistoriqueBinding) : RecyclerView.ViewHolder(binding.root) {

    private val format = SimpleDateFormat("dd/MM/yyyy")

    fun setData(historique: Historique) {
        binding.DateD.text = format.format(historique.DateD)
        binding.DateF.text = format.format(historique.DateF)
        binding.Total.text = historique.Total.toString()
    }
}