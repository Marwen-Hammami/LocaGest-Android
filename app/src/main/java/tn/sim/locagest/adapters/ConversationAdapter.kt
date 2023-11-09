package tn.sim.locagest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import tn.sim.locagest.databinding.CardMessageSentBinding
import tn.sim.locagest.models.Message
import tn.sim.locagest.viewholder.ConversationOneViewHolder

class ConversationAdapter(val message: List<Message>)
    : RecyclerView.Adapter<ConversationOneViewHolder>() {

    lateinit var binding: CardMessageSentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationOneViewHolder {
        binding = CardMessageSentBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)

        return ConversationOneViewHolder(binding)
    }

    override fun getItemCount(): Int = message.size

    override fun onBindViewHolder(holder: ConversationOneViewHolder, position: Int) {
        val mess = message[position]

//        binding.aImage1.visibility = View.GONE
//        binding.bLayout.visibility = View.GONE
//        binding.cLayout.visibility = View.GONE

        holder.setData(mess)
    }

}