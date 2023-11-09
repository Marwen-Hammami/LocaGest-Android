package tn.sim.locagest.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.ViewHolders.HistoriqueViewHolder
import tn.sim.locagest.databinding.ItemHistoriqueBinding


class HistoriqueAdapter(val historiquelist : List<Historique>) : RecyclerView.Adapter<HistoriqueViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriqueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriqueBinding.inflate(inflater, parent, false)
        return HistoriqueViewHolder(binding)
    }


    override fun getItemCount()  = historiquelist.size


    override fun onBindViewHolder(holder: HistoriqueViewHolder, position: Int) {
        val historique = historiquelist[position]
        holder.setData(historique)    }

}