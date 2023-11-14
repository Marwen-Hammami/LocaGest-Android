package tn.sim.locagest.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardConversationUserBinding
import tn.sim.locagest.models.Conversation
import tn.sim.locagest.ui.activity.ConversationOneActivity
import tn.sim.locagest.viewholder.ConversationsListViewHolder

class ConversationsListAdapter(val conversations: MutableList<Conversation>): RecyclerView.Adapter<ConversationsListViewHolder>() {

    lateinit var binding: CardConversationUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationsListViewHolder {
        binding = CardConversationUserBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)

        return ConversationsListViewHolder(binding)
    }

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: ConversationsListViewHolder, position: Int) {
        val conv = conversations[position]

        if (conv.isGroup){
            binding.nomPrenom.text = conv.name
        } else {
            //id du membre different du current user
            binding.nomPrenom.text = conv.members[0]
        }

        binding.root.setOnClickListener {
            val context = binding.root.context
            val intent = Intent(context, ConversationOneActivity::class.java)
            intent.putExtra("convId", conversations[position]._id)
            context.startActivity(intent)
        }
    }

}