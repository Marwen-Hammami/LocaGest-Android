package tn.sim.locagest.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardConversationUserBinding
import tn.sim.locagest.models.User
import tn.sim.locagest.ui.activity.ConversationOneActivity
import tn.sim.locagest.viewholder.ConversationsListViewHolder

class ConversationUserListAdapter(val users: ArrayList<User>): RecyclerView.Adapter<ConversationsListViewHolder>() {

    lateinit var binding: CardConversationUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationsListViewHolder {
        binding = CardConversationUserBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)

        return ConversationsListViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ConversationsListViewHolder, position: Int) {
        val user = users[position]

        binding.nomPrenom.text = user.firstName + " " + user.lastName

        binding.root.setOnClickListener {
            //create conversation if not exist
            val context = binding.root.context
            val intent = Intent(context, ConversationOneActivity::class.java)
            //intent.putExtra("userId", user._id)
            context.startActivity(intent)
        }
    }

}