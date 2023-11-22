package tn.sim.locagest.api
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import io.socket.client.IO
import io.socket.client.Socket
import tn.sim.locagest.models.Message
import tn.sim.locagest.models.User
import tn.sim.locagest.ui.MainActivityChat
import tn.sim.locagest.ui.activity.ConversationOneActivity
import tn.sim.locagest.viewmodel.MessageViewModel
import java.net.URISyntaxException
import java.util.Date

object SocketManager {
    private const val SERVER_URL = "http://10.0.2.2:9090"
    private lateinit var mSocket: Socket

    init {
        try {
            mSocket = IO.socket(SERVER_URL)
        } catch (e: URISyntaxException) {
            Log.e("SocketManager", "Socket initialization failed: ${e.message}")
        }
    }

    fun connect() {
        mSocket.connect()
        listenForConnectEvent()
    }

    fun disconnect() {
        mSocket.disconnect()
    }

    fun addUser(userId: String) {
        mSocket.emit("addUser", userId)
    }

    private fun listenForConnectEvent() {
        mSocket.on(Socket.EVENT_CONNECT) {
            Log.w("SocketManager", "Socket connected")
            addUser(User.currentUser._id.toString())
        }
    }

    // Add more methods for other socket-related functionality (e.g., sendMessage)
    fun sendMessage(senderId: String, receiverId: String, text: String) {
        mSocket.emit("sendMessage", mapOf("senderId" to senderId, "receiverId" to receiverId, "text" to text))
        Log.w("SocketManager", "Send message")
    }

    fun listenForGetMessageEvent(
        messageViewModel: MessageViewModel
    ) {
        mSocket.on("getMessage") {
            // Handle the received message
            val receivedMessage = it[0].toString()
            Log.w("SocketManager", "Received message: $receivedMessage")
            // Add your logic to process the received message
            val newMessage = Message("","","",receivedMessage, listOf(), Date(),Date())

            var currentMessages = messageViewModel.recyclerListData.value?.toMutableList()

            currentMessages?.add(newMessage)

            messageViewModel.recyclerListData.postValue(currentMessages)
        }
    }

    fun getInstance(): Socket {
        return mSocket
    }
}