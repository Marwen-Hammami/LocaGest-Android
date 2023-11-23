package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.api.ConversationService
import tn.sim.locagest.api.retrofit.RetroInstance
import tn.sim.locagest.models.Conversation

class ConversationViewModel: ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<Conversation>?>
    lateinit var createLiveData: MutableLiveData<Conversation?>
    lateinit var updateLiveData: MutableLiveData<Conversation?>

    init {
        recyclerListData = MutableLiveData()
        createLiveData = MutableLiveData()
        updateLiveData = MutableLiveData()
    }

    fun getConversationListOfAUserObservable(): MutableLiveData<List<Conversation>?> {
        return recyclerListData
    }

    fun getCreateNewConversationObservable(): MutableLiveData<Conversation?> {
        return createLiveData
    }

    fun getUpdateConversationObservable(): MutableLiveData<Conversation?> {
        return updateLiveData
    }

    fun getLessonConversation(id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ConversationService::class.java)
        val call = retroInstance.getConversationsOfAUser(id)
        call.enqueue(object : Callback<List<Conversation>> {
            override fun onResponse(
                call: Call<List<Conversation>>,
                response: Response<List<Conversation>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Conversation>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.e("MyApp", "Conversation list: "+ t.message.toString())
                }
            }
        })
    }

    fun createConversation(conv: Conversation){
        val retroInstance = RetroInstance.getRetroInstance().create(ConversationService::class.java)
        val call = retroInstance.createConversation(conv)
        call.enqueue(object : Callback<Conversation> {
            override fun onResponse(
                call: Call<Conversation>,
                response: Response<Conversation>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Conversation>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Conversation create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateConversation(_id: String, conv: Conversation){
        val retroInstance = RetroInstance.getRetroInstance().create(ConversationService::class.java)
        val call = retroInstance.updateConversation(_id, conv)
        call.enqueue(object : Callback<Conversation> {
            override fun onResponse(
                call: Call<Conversation>,
                response: Response<Conversation>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Conversation>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Conversation Update: "+ t.message.toString())
                }
            }
        })
    }
}