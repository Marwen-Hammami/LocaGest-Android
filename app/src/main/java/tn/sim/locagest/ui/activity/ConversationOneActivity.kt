package tn.sim.locagest.ui.activity

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService
import tn.sim.locagest.adapters.ConversationAdapter
import tn.sim.locagest.databinding.ActivityConversationOneBinding
import tn.sim.locagest.models.Conversation
import tn.sim.locagest.models.Message
import tn.sim.locagest.models.User
import tn.sim.locagest.viewmodel.MessageViewModel

class ConversationOneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConversationOneBinding

    //For Subjects ViwModel
    lateinit var messageViewModel: MessageViewModel
    lateinit var listMessagess: MutableList<Message>

    lateinit var conv: Conversation

    //for image pick
    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
//                selectedImage.setImageURI(imgUri)
                binding.icSend.setOnClickListener {
                    var mess = Message(null, conv._id!!, User.currentUser._id!!
                        , binding.textToSend.text.toString()
                        , null, null, null
                    )
                    messageViewModel.createMessageWithImage(mess, imgUri!!)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //récupérer l'id de la conv passé en intent
        conv = (intent.getSerializableExtra("conv") as? Conversation)!!

        if (conv != null) {
            conv._id?.let { initViewModel(it) }
        }

        initSecondToolbar(conv)

    }

    private fun initViewModel(convId: String) {
        listMessagess = mutableListOf()
        messageViewModel = ViewModelProvider(this).get(MessageViewModel::class.java)

        messageViewModel.getMessagesOfConv(convId)

        messageViewModel.getMessagesOfConvObservable().observe(this, Observer<List<Message>?> {
            if(it == null) {
                Log.w("MyApp", "There is no Messages")
            }else {
                listMessagess = it.toMutableList()
                initRecyclerView()
            }
        })
    }

    fun initRecyclerView() {
        val myAdapter = ConversationAdapter(listMessagess.reversed().toTypedArray(), messageViewModel)  //to get the latest message at the bottom

        binding.rvMessages.adapter = myAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true          //to get the latest message at the bottom
        binding.rvMessages.layoutManager = layoutManager

        sendIconClick()
    }

    private fun sendIconClick() {
        binding.icSend.setOnClickListener{
            val newMessage = Message(
                null,
                conv._id!!,
                User.currentUser._id!!,
                binding.textToSend.text.toString(),
                null,
                null,
                null,
                )

            messageViewModel.createMessage(newMessage)

            binding.textToSend.text.clear()

            messageViewModel.getMessagesOfConv(conv._id!!)
        }
    }

    private fun initSecondToolbar(conv: Conversation?) {
        //Conversation Name
        if (conv != null) {
            if (conv.isGroup){
                binding.nomPrenom.text = conv.name
            } else {
                //id du membre different du current user
                if (User.currentUser._id == conv.members[0]){
                    //getUser(conv.members[1])
                    binding.nomPrenom.text = conv.members[1]
                } else {
                    //getUser(conv.members[0])
                    binding.nomPrenom.text = conv.members[0]
                }

            }
        }

        //Return Arrow
        binding.backArrow.setOnClickListener {
            finish()
        }

        binding.videoCall.setOnClickListener {
            videoCall()
        }

        //Select File
        binding.importFile.setOnClickListener {
            pickImage()
        }
    }

    private fun videoCall() {
        //add if conv use groupid
        //the other person you want to call
        var otherusername = ""
        if (conv.members[0] == User.currentUser._id) {
            otherusername = conv.members[1]
        } else {
            otherusername = conv.members[0]
        }

        val username = User.currentUser.username

        val intent = Intent(this, CallingActivity::class.java)
        startActivity(intent)

        val application: Application = application // Android's application context
        val appID: Long = 988207839 // yourAppID
        val appSign: String = "c855361eabefa96a3cbe617ffaf228d73272c68c029a260774d38b3448af8e7f" // yourAppSign

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true
        val notificationConfig = ZegoNotificationConfig()
        notificationConfig.sound = "zego_uikit_sound_call"
        notificationConfig.channelID = otherusername
        notificationConfig.channelName = otherusername   //if group pass name else other member name
        ZegoUIKitPrebuiltCallInvitationService.init(
            application,
            appID,
            appSign,
            username,
            username,
            callInvitationConfig
        )
    }

    private fun pickImage() {
        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
    }


    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallInvitationService.unInit()
    }
}