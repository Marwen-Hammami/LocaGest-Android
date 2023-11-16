package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.api.ConversationService
import tn.sim.locagest.api.MessageService
import tn.sim.locagest.api.retrofit.RetroInstance
import tn.sim.locagest.models.Conversation
import tn.sim.locagest.models.Message

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
    /*
    fun uploadImage(messageId: String, imgUri: Uri, callback: (Response<YourResponseModel>) -> Unit) {
        val imageFile = File(imgUri.path)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

        val call: Call<YourResponseModel> = messageService.uploadImage(messageId, imagePart)

        call.enqueue(object : Callback<YourResponseModel> {
            override fun onResponse(call: Call<YourResponseModel>, response: Response<YourResponseModel>) {
                callback(response)
            }

            override fun onFailure(call: Call<YourResponseModel>, t: Throwable) {
                // Handle failure
            }
        })
    }
     */

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