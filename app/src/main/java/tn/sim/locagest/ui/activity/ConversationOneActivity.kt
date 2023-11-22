package tn.sim.locagest.ui.activity

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService
import tn.sim.locagest.adapters.ConversationAdapter
import tn.sim.locagest.api.SocketManager
import tn.sim.locagest.databinding.ActivityConversationOneBinding
import tn.sim.locagest.models.Conversation
import tn.sim.locagest.models.Message
import tn.sim.locagest.models.User
import tn.sim.locagest.util.RealPathUtil
import tn.sim.locagest.viewmodel.MessageViewModel


class ConversationOneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConversationOneBinding

    var path: String? = null

    private val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 123

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_MEDIA_IMAGES
    )

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
                val context: Context = this
                path = RealPathUtil.getRealPath(context, imgUri)
                Log.w("f",imgUri.toString())
                binding.icSend.setOnClickListener {
                    var mess = Message(null, conv._id!!, User.currentUser._id!!
                        , binding.textToSend.text.toString()
                        , null, null, null
                    )

                    messageViewModel.createMessageWithImage(mess, path)
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get storage permission
        val permission =
            ActivityCompat.checkSelfPermission(this@ConversationOneActivity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                this@ConversationOneActivity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
        //Message create: /storage/emulated/0/Pictures/IMG_20231116_162629.jpg: open failed: EACCES (Permission denied)

        //récupérer l'id de la conv passé en intent
        conv = (intent.getSerializableExtra("conv") as? Conversation)!!

        if (conv != null) {
            conv._id?.let { initViewModel(it) }
        }

        initSecondToolbar(conv)

        SocketManager.listenForGetMessageEvent(messageViewModel)
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
        //get other person id for socket
        var receiver = ""
        if (User.currentUser._id == conv.members[0]){
            receiver = conv.members[1]
        } else {
            receiver = conv.members[0]
        }
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

            messageViewModel.createMessage(newMessage,receiver)

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
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_EXTERNAL_STORAGE_PERMISSION_CODE
        )


        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallInvitationService.unInit()
    }
}