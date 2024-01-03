package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.App
import tn.sim.locagest.models.Agence

class AgenceViewModel : ViewModel() {

    var recyclerListData: MutableLiveData<List<Agence>?> = MutableLiveData()
    var createLiveData: MutableLiveData<Agence?> = MutableLiveData()
    var updateLiveData: MutableLiveData<Agence?> = MutableLiveData()
    var deleteLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val retroInstance = App.AgenceServiceRetroInstance

    fun getAgenceListObservable(): MutableLiveData<List<Agence>?> {
        return recyclerListData
    }

    fun getCreateNewAgenceObservable(): MutableLiveData<Agence?> {
        return createLiveData
    }

    fun getUpdateAgenceObservable(): MutableLiveData<Agence?> {
        return updateLiveData
    }

    fun deleteAgenceObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getAll() {
        val call = retroInstance.getAll()
        call.enqueue(object : Callback<List<Agence>> {
            override fun onResponse(
                call: Call<List<Agence>>,
                response: Response<List<Agence>>
            ) {
                Log.w("response, ", response.toString())
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                    Log.d("agence", recyclerListData.toString())

                } else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Agence>>, t: Throwable) {
                recyclerListData.postValue(null)
                Log.d("MyApp", "Agence list: " + t.message.toString())
            }
        })
    }

    fun create(agence: Agence) {
        Log.w("in viewmodel ", agence.toString())

        val call = retroInstance.create(agence)
        call.enqueue(object : Callback<Agence> {
            override fun onResponse(
                call: Call<Agence>,
                response: Response<Agence>
            ) {
                Log.w("response, ", response.toString())
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                } else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Agence>, t: Throwable) {
                createLiveData.postValue(null)
                Log.d("MyApp", "Subject create: " + t.message.toString())
            }
        })
    }

    fun update(id: String, agence: Agence) {
        val call = retroInstance.update(id, agence)
        call.enqueue(object : Callback<Agence> {
            override fun onResponse(
                call: Call<Agence>,
                response: Response<Agence>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                } else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Agence>, t: Throwable) {
                updateLiveData.postValue(null)
                Log.d("MyApp", "Subject Update: " + t.message.toString())
            }
        })
    }

    fun delete(id: String) {
        val call = retroInstance.delete(id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Agence Delete: " + t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failuagence
            }
        })
    }
}