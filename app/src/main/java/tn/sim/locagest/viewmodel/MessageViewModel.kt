package tn.sim.locagest.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.api.ConversationService
import tn.sim.locagest.api.MessageService
import tn.sim.locagest.api.retrofit.RetroInstance
import tn.sim.locagest.models.Conversation
import tn.sim.locagest.models.Message
import java.io.File

class MessageViewModel: ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<Message>?>
    lateinit var createLiveData: MutableLiveData<Message?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>

    init {
        recyclerListData = MutableLiveData()
        createLiveData = MutableLiveData()
        deleteLiveData = MutableLiveData()
    }

    fun getMessagesOfConvObservable(): MutableLiveData<List<Message>?> {
        return recyclerListData
    }

    fun getCreateNewMessageObservable(): MutableLiveData<Message?> {
        return createLiveData
    }

    fun deleteMessageObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getMessagesOfConv(id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(MessageService::class.java)
        val call = retroInstance.getMessagesOfAConversation(id)
        call.enqueue(object : Callback<List<Message>> {
            override fun onResponse(
                call: Call<List<Message>>,
                response: Response<List<Message>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.e("MyApp", "Message list: " + t.message.toString())
                }
            }
        })
    }

    fun createMessage(mess: Message){
        val retroInstance = RetroInstance.getRetroInstance().create(MessageService::class.java)
        val call = retroInstance.createMessage(mess)
        call.enqueue(object : Callback<Message> {
            override fun onResponse(
                call: Call<Message>,
                response: Response<Message>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Message>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Message create: "+ t.message.toString())
                }
            }
        })
    }
    fun createMessageWithImage(mess: Message, imgUri: Uri){
        val retroInstance = RetroInstance.getRetroInstance().create(MessageService::class.java)

        // Convert Message object to RequestBody
        val paramsRequestBody = Gson().toJson(mess).toRequestBody("application/json".toMediaTypeOrNull())

//        // Use ContentResolver to open an input stream for the content URI
//        val inputStream = contentResolver.openInputStream(imgUri)
//        val requestFile = inputStream?.use { it.readBytes().toRequestBody("multipart/form-data".toMediaTypeOrNull()) }

        val imageFile = File(imgUri.path)
        val mediaType = "multipart/form-data".toMediaTypeOrNull()
        val requestFile = imageFile.asRequestBody(mediaType)
        val imagePart = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)

        val call = retroInstance.createMessageWithImage(paramsRequestBody, imagePart)
        call.enqueue(object : Callback<Message> {
            override fun onResponse(
                call: Call<Message>,
                response: Response<Message>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Message>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Message create: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteMessage(_id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(MessageService::class.java)
        val call = retroInstance.deleteMessage(_id)
        call.enqueue(object : Callback<Message> {
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Message>, t: Throwable) {
                Log.d("MyApp", "Message Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }
}