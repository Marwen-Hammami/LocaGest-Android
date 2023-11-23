package tn.sim.locagest.viewholder

import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardConversationUserBinding

class ConversationsListViewHolder(val cardConversationUserBinding: CardConversationUserBinding):
    RecyclerView.ViewHolder(cardConversationUserBinding.root){

    val textView = cardConversationUserBinding.nomPrenom

}