package tn.sim.locagest.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardAgenceBinding
import tn.sim.locagest.models.Agence
import tn.sim.locagest.ui.activity.AgenceDetailsActivity
import tn.sim.locagest.ui.activity.ManageAgenceActivity
import tn.sim.locagest.viewmodel.AgenceViewModel

class AgenceAdapter(
    private val agences: List<Agence>,
    private val agenceViewModel: AgenceViewModel
) :
    RecyclerView.Adapter<AgenceAdapter.AgenceViewHolder>(), View.OnClickListener {

    inner class AgenceViewHolder(val binding: CardAgenceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgenceViewHolder {
        val binding = CardAgenceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return AgenceViewHolder(binding)
    }

    override fun getItemCount(): Int = agences.size

    override fun onBindViewHolder(holder: AgenceViewHolder, position: Int) {
        val agence = agences[position]

        holder.binding.apply {
            nomTV.text = agence.name
            addressTV.text = agence.address
            headTV.text = agence.head?.email

            deleteButton.setOnClickListener {
                agence.id?.let { agenceViewModel.delete(it) }
            }
            updateButton.setOnClickListener {
                val intent = Intent(holder.itemView.context, ManageAgenceActivity::class.java)
                intent.putExtra("agence", agence)
                holder.itemView.context.startActivity(intent)
            }
            displayDetails.setOnClickListener {
                val intent = Intent(holder.itemView.context, AgenceDetailsActivity::class.java)
                intent.putExtra("agence", agence)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun onClick(view: View) {
        val position = view.tag as Int
    }
}
