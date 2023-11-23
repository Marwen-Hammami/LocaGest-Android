package tn.sim.locagest.viewholder

import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardMessageSentBinding
import tn.sim.locagest.models.Message

class ConversationOneViewHolder(val cardMessageSentBinding: CardMessageSentBinding):
    RecyclerView.ViewHolder(cardMessageSentBinding.root){

    val textView = cardMessageSentBinding.messageText

}