package tn.sim.locagest.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tn.sim.locagest.R
import tn.sim.locagest.databinding.CardMessageSentBinding
import tn.sim.locagest.models.Message
import tn.sim.locagest.models.User
import tn.sim.locagest.viewholder.ConversationOneViewHolder
import tn.sim.locagest.viewmodel.MessageViewModel

class ConversationAdapter(val message: Array<Message>, val messageViewModel: MessageViewModel): RecyclerView.Adapter<ConversationOneViewHolder>() {

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

        //Manage color and gravity of a message
        if (mess.sender == User.currentUser._id) {
            //Change Message color
            val colorResId = R.color.accent_color
            val color = ContextCompat.getColor(holder.itemView.context, colorResId)
            binding.messageCard.setCardBackgroundColor(color)

            //make it right aligned     //do not work
            val linearLayout = binding.messageCard.findViewById<LinearLayout>(R.id.messageLL)
            linearLayout.gravity = Gravity.END
        }

        //Manage text Display
        if (mess.text?.isEmpty() == true) {
            binding.messageText.visibility = View.GONE
        } else {
            holder.textView.text = mess.text
        }

        //Manage files display
        manageFiles(mess)

        deleteMessage(mess)

    }

    private fun manageFiles(mess: Message) {
        when (mess.file?.size) {
            1 -> {
                binding.aImage1.visibility = View.VISIBLE
                binding.bLayout.visibility = View.GONE
                binding.cLayout.visibility = View.GONE

                val imageUrl = mess.file[0]
                val modifiedimageUrl = imageUrl.replace("localhost", "10.0.2.2")
                Picasso.get().load(modifiedimageUrl).into(binding.aImage1)
            }
            2 -> {
                binding.aImage1.visibility = View.GONE
                binding.bLayout.visibility = View.VISIBLE
                binding.cLayout.visibility = View.GONE

                //premiere image
                val imageUrl1 = mess.file[0]
                val modifiedimageUrl1 = imageUrl1.replace("localhost", "10.0.2.2")
                Picasso.get().load(modifiedimageUrl1).into(binding.bImage1)
                //deuxieme image
                val imageUrl2 = mess.file[1]
                val modifiedimageUrl2 = imageUrl2.replace("localhost", "10.0.2.2")
                Picasso.get().load(modifiedimageUrl2).into(binding.bImage2)
            }
            3 -> {
                binding.aImage1.visibility = View.GONE
                binding.bLayout.visibility = View.GONE
                binding.cLayout.visibility = View.VISIBLE

                //premiere image
                val imageUrl11 = mess.file[0]
                val modifiedimageUrl11 = imageUrl11.replace("localhost", "10.0.2.2")
                Picasso.get().load(modifiedimageUrl11).into(binding.cImage1)
                //deuxieme image
                val imageUrl2 = mess.file[1]
                val modifiedimageUrl2 = imageUrl2.replace("localhost", "10.0.2.2")
                Picasso.get().load(modifiedimageUrl2).into(binding.cImage2)
                //troisieme image
                val imageUrl3 = mess.file[2]
                val modifiedimageUrl3 = imageUrl3.replace("localhost", "10.0.2.2")
                Picasso.get().load(modifiedimageUrl3).into(binding.cImage3)
            }
            else -> {
                //aucun fichier
                binding.aImage1.visibility = View.GONE
                binding.bLayout.visibility = View.GONE
                binding.cLayout.visibility = View.GONE
            }
        }
    }

    private fun deleteMessage(mess: Message) {
        binding.messageCard.setOnLongClickListener {

            mess._id?.let { messageViewModel.deleteMessage(it) }

            messageViewModel.getMessagesOfConv(mess.conversationId)

            false
        }
    }

}