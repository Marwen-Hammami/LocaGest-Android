package tn.sim.locagest.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.Model.MethodeP
import tn.sim.locagest.databinding.ItemMethodePaiementBinding
import java.text.SimpleDateFormat


class MethodePaiementViewHolder(private val binding: ItemMethodePaiementBinding) : RecyclerView.ViewHolder(binding.root) {

    private val format = SimpleDateFormat("dd/MM/yyyy")

    fun setData(paiement: MethodeP) {
       TODO()
    }
}
