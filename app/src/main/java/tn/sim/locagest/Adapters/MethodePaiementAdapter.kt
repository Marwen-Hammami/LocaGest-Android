package tn.sim.locagest.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.models.MethodeP
import tn.sim.locagest.ViewHolders.MethodePaiementViewHolder
import tn.sim.locagest.databinding.ItemMethodePaiementBinding


class MethodePaiementAdapter(val paiementList: MutableList<MethodeP>) : RecyclerView.Adapter<MethodePaiementViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodePaiementViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMethodePaiementBinding.inflate(inflater, parent, false)
        return MethodePaiementViewHolder(binding)
    }


    override fun getItemCount()  = paiementList.size


    override fun onBindViewHolder(holder: MethodePaiementViewHolder, position: Int) {
        //val paiement = paiementList[position]
        //holder.setData(paiement)
        TODO()
    }

}
