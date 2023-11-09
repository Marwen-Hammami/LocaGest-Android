package tn.sim.locagest.viewholder

import androidx.recyclerview.widget.RecyclerView
import tn.sim.locagest.databinding.CardMessageSentBinding
import tn.sim.locagest.models.Message

class ConversationOneViewHolder(val cardMessageSentBinding: CardMessageSentBinding)
    : RecyclerView.ViewHolder(cardMessageSentBinding.root){

        fun setData(message: Message) {
            cardMessageSentBinding.messageText.text = message.text
//            if (!message.text.isNullOrBlank()) {
//                cardMessageSentBinding.messageText.text = message.text
//            }
//            if (!message.file.isNullOrEmpty()) {
////                cardMessageSentBinding.aImage1.setImageResource(message.file[0])
//            }
        }
}