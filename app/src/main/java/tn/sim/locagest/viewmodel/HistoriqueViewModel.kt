package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.App
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.api.HistoriqueService

class HistoriqueViewModel:  ViewModel() {
    var recyclerListData: MutableLiveData<List<Historique>?> = MutableLiveData()
    var createLiveData: MutableLiveData<Historique?> = MutableLiveData()
    var updateLiveData: MutableLiveData<Historique?> = MutableLiveData()
    var deleteLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val retroInstance = App.HistoriqueServiceRetroInstance

    fun getHistoriqueListObservable(): MutableLiveData<List<Historique>?> {
        return recyclerListData
    }

    fun getCreateNewHistoriqueObservable(): MutableLiveData<Historique?> {
        return createLiveData
    }

    fun getUpdateHistoriqueObservable(): MutableLiveData<Historique?> {
        return updateLiveData
    }

    fun deleteHistoriqueObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getHistoriqueList() {
        val call = retroInstance.getHistoriques()
        call.enqueue(object : Callback<List<Historique>> {
            override fun onResponse(
                call: Call<List<Historique>>,
                response: Response<List<Historique>>
            ) {
                Log.w("response, ",response.toString())
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Historique>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Historique list: "+ t.message.toString())
                }
            }
        })
    }

    fun createHistorique(histo: Historique){
        val call = retroInstance.createHistoriques(histo)
        call.enqueue(object : Callback<Historique> {
            override fun onResponse(
                call: Call<Historique>,
                response: Response<Historique>
            ) {
                Log.w("response, ",response.toString())
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Historique>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateHistorique(_id: String, res: Historique){
        val call = retroInstance.updateHistoriques(_id, res)
        call.enqueue(object : Callback<Historique> {
            override fun onResponse(
                call: Call<Historique>,
                response: Response<Historique>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Historique>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteHistorique(_id: String) {
        val call = retroInstance.deleteHistoriques(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Historique Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }
}