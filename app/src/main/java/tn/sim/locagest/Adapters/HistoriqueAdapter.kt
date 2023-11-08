package tn.sim.locagest.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.ItemHistoriqueBinding
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.ViewHolders.HistoriqueViewHolder


class HistoriqueAdapter(val historiquelist : List<Historique>) : RecyclerView.Adapter<HistoriqueViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriqueViewHolder {
        val binding = inflate(LayoutInflater.from(parent.context), parent, false)
     return HistoriqueViewHolder(binding)
    }

    override fun getItemCount()  = historiquelist.size


    override fun onBindViewHolder(holder: HistoriqueViewHolder, position: Int) {
        val historique = historiquelist[position]
        holder.setData(historique)    }

}