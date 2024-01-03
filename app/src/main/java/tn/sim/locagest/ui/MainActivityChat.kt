package tn.sim.locagest.ui

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService
import tn.sim.locagest.databinding.ActivityMainChatBinding
import tn.sim.locagest.models.User
import tn.sim.locagest.ui.fragments.ConversationListFragment
import io.socket.client.Socket;
import tn.sim.locagest.MainActivityFlotte
import tn.sim.locagest.MainActivityReservation
import tn.sim.locagest.R
import tn.sim.locagest.api.SocketManager
import tn.sim.locagest.ui.activity.AgenceListActivity
import tn.sim.locagest.ui.activity.UserProfile
import java.net.URISyntaxException


class MainActivityChat : AppCompatActivity() {

    private lateinit var binding : ActivityMainChatBinding

    //socket
    private lateinit var mSocket : Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //init video call so you can receive call from the moment you log in
        initvideoCall()

        //init socket
        initSocket()

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(tn.sim.locagest.R.id.fragmentListConvsContainerView, ConversationListFragment()).commit()

    }

    override fun onDestroy() {
        super.onDestroy()
        SocketManager.disconnect()
    }

    private fun initSocket() {
        try {
            SocketManager.connect()
        } catch (e: URISyntaxException ) {}
    }

    private fun initvideoCall() {
        val username = User.currentUser.username
        val userId = User.currentUser._id

        val application: Application = application // Android's application context
        val appID: Long = 988207839 // yourAppID
        val appSign: String = "c855361eabefa96a3cbe617ffaf228d73272c68c029a260774d38b3448af8e7f" // yourAppSign

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
       // callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true
        val notificationConfig = ZegoNotificationConfig()
        notificationConfig.sound = "zego_uikit_sound_call"
        notificationConfig.channelID = userId
        notificationConfig.channelName = userId   //if group pass name else other member name
        ZegoUIKitPrebuiltCallInvitationService.init(
            application,
            appID,
            appSign,
            username,
            username,
            callInvitationConfig
        )
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(tn.sim.locagest.R.menu.btm_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        return when (item.itemId) {
            tn.sim.locagest.R.id.CarPage -> {
                intent = Intent(this, MainActivityFlotte::class.java)
                startActivity(intent)
                true
            }

            tn.sim.locagest.R.id.UserPage -> {
                intent = Intent(this, UserProfile::class.java)
                startActivity(intent)
                true
            }
            tn.sim.locagest.R.id.ChatPage-> {
                intent = Intent(this, MainActivityChat::class.java)
                startActivity(intent)
                true
            }
            tn.sim.locagest.R.id.ResPage-> {
                intent = Intent(this, MainActivityReservation::class.java)
                startActivity(intent)
                true
            }
            tn.sim.locagest.R.id.AgencePage-> {
                intent = Intent(this, AgenceListActivity::class.java)
                startActivity(intent)
                true
            }
            /* tn.sim.locagest.R.id.GaragePage-> {
                 intent = Intent(this, ::class.java)
                 startActivity(intent)
                 true
             }*/

            else -> super.onOptionsItemSelected(item)
        }
    }
}