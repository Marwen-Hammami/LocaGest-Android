package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.api.HistoriqueService
import tn.sim.locagest.api.retrofit.RetroInstance

class HistoriqueViewModel:  ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<Historique>?>
    lateinit var createLiveData: MutableLiveData<Historique?>
    lateinit var updateLiveData: MutableLiveData<Historique?>
    lateinit var deleteLiveData: MutableLiveData<Boolean>

    init {
        recyclerListData = MutableLiveData()
        createLiveData = MutableLiveData()
        updateLiveData = MutableLiveData()
        deleteLiveData = MutableLiveData()
    }

    fun getReservationListObservable(): MutableLiveData<List<Historique>?> {
        return recyclerListData
    }

    fun getCreateNewReservationObservable(): MutableLiveData<Historique?> {
        return createLiveData
    }

    fun getUpdateReservationObservable(): MutableLiveData<Historique?> {
        return updateLiveData
    }

    fun deleteReservationObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getReservationList() {
        val retroInstance = RetroInstance.getRetroInstance().create(HistoriqueService::class.java)
        val call = retroInstance.getReservations()
        call.enqueue(object : Callback<List<Historique>> {
            override fun onResponse(
                call: Call<List<Historique>>,
                response: Response<List<Historique>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Historique>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Reservation list: "+ t.message.toString())
                }
            }
        })
    }

    fun createReservation(histo: Historique){
        val retroInstance = RetroInstance.getRetroInstance().create(HistoriqueService::class.java)
        val call = retroInstance.createReservations(histo)
        call.enqueue(object : Callback<Historique> {
            override fun onResponse(
                call: Call<Historique>,
                response: Response<Historique>
            ) {
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

    fun updateReservation(_id: String, res: Historique){
        val retroInstance = RetroInstance.getRetroInstance().create(HistoriqueService::class.java)
        val call = retroInstance.updateReservations(_id, res)
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

    fun deleteReservation(_id: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(HistoriqueService::class.java)
        val call = retroInstance.deleteReservations(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Reservation Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }
}