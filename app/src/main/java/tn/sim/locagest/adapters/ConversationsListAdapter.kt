package tn.sim.locagest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardConversationUserBinding
import tn.sim.locagest.models.User
import tn.sim.locagest.viewholder.ConversationsListViewHolder

class ConversationsListAdapter(val users: ArrayList<User>): RecyclerView.Adapter<ConversationsListViewHolder>() {

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
            Log.w("click", "click")
            Log.w("click", user.firstName.toString())
            Log.w("click", user._id)
        }
    }

}