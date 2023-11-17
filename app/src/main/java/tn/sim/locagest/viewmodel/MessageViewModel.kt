package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.api.MessageService
import tn.sim.locagest.api.retrofit.RetroInstance
import tn.sim.locagest.models.Message
import java.io.File

class MessageViewModel: ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<Message>?>
    lateinit var createLiveData: MutableLiveData<Message?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>

    private val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 123

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
    fun createMessageWithImage(mess: Message, path: String?){
        val retroInstance = RetroInstance.getRetroInstance().create(MessageService::class.java)

        // Convert Message object to RequestBody
//        val paramsRequestBody = Gson().toJson(mess).toRequestBody("application/json".toMediaTypeOrNull())

        val imageFile = File(path)
                    val mediaType = "multipart/form-data".toMediaTypeOrNull()
                    val requestFile = imageFile.asRequestBody(mediaType)
                    val imagePart =
                        requestFile?.let { MultipartBody.Part.createFormData("file", "testName", it) }

        val call = imagePart?.let { mess.text?.let { it1 ->
            retroInstance.createMessageWithImage(mess.conversationId, mess.sender,
                it1, it)
        } }
        if (call != null) {
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
                    if (t != null) {
                        Log.e("MyApp", "Message create: "+ t.message.toString())    //MyApp tn.sim.locagest D  Message create: /storage/emulated/0/Pictures/Screenshots/Screenshot_20231116-221709.png: open failed: EACCES (Permission denied)
                    }
                }
            })
        }
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