package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.App
import tn.sim.locagest.Model.Reservation
import tn.sim.locagest.api.ReservationService

class ReservationViewModel:  ViewModel() {
    var recyclerListData: MutableLiveData<List<Reservation>?> = MutableLiveData()
    var createLiveData: MutableLiveData<Reservation?> = MutableLiveData()
    var updateLiveData: MutableLiveData<Reservation?> = MutableLiveData()
    var deleteLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val retroInstance = App.ReservationServiceRetroInstance

    fun getReservationListObservable(): MutableLiveData<List<Reservation>?> {
        return recyclerListData
    }

    fun getCreateNewReservationObservable(): MutableLiveData<Reservation?> {
        return createLiveData
    }

    fun getUpdateReservationObservable(): MutableLiveData<Reservation?> {
        return updateLiveData
    }

    fun deleteReservationObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getReservationList() {
        val call = retroInstance.getReservations()
        call.enqueue(object : Callback<List<Reservation>> {
            override fun onResponse(
                call: Call<List<Reservation>>,
                response: Response<List<Reservation>>
            ) {
                Log.w("response, ",response.toString())
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                    Log.d("res", recyclerListData.toString())

                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Reservation>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Reservation list: "+ t.message.toString())
                }
            }
        })
    }

    fun createReservation(res: Reservation){
        Log.w("in viewmodel ",res.toString())

        val call = retroInstance.createReservations(res)
        call.enqueue(object : Callback<Reservation> {
            override fun onResponse(
                call: Call<Reservation>,
                response: Response<Reservation>
            ) {
                Log.w("response, ",response.toString())
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Reservation>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject create: "+ t.message.toString())
                }
            }
        })
    }

    fun updateReservation(_id: String, res: Reservation){
        val call = retroInstance.updateReservations(_id, res)
        call.enqueue(object : Callback<Reservation> {
            override fun onResponse(
                call: Call<Reservation>,
                response: Response<Reservation>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Reservation>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deleteReservation(_id: String) {
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