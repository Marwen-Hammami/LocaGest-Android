package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.App
import tn.sim.locagest.models.Historique
import tn.sim.locagest.models.Paiement

class PaiementViewModel : ViewModel()  {


    var recyclerListData: MutableLiveData<List<Paiement>?> = MutableLiveData()
    var createLiveData: MutableLiveData<Paiement?> = MutableLiveData()
    var updateLiveData: MutableLiveData<Paiement?> = MutableLiveData()
    var deleteLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val retroInstance = App.PaiementServiceRetroInstance

    fun getPaiementListObservable(): MutableLiveData<List<Paiement>?> {
        return recyclerListData
    }

    fun getCreateNewPaiementObservable(): MutableLiveData<Paiement?> {
        return createLiveData
    }

    fun getUpdatePaiementObservable(): MutableLiveData<Paiement?> {
        return updateLiveData
    }

    fun deletePaiementObservable(): MutableLiveData<Boolean> {
        return deleteLiveData
    }

    fun getPaiementList() {
        val call = retroInstance.getPaiement()
        call.enqueue(object : Callback<List<Paiement>> {
            override fun onResponse(
                call: Call<List<Paiement>>,
                response: Response<List<Paiement>>
            ) {
                Log.w("response, ",response.toString())
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Paiement>>, t: Throwable?) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Paiement list: "+ t.message.toString())
                }
            }
        })
    }

    fun createPaiement(pai: Paiement){
        val call = retroInstance.createPaiement(pai)
        call.enqueue(object : Callback<Paiement> {
            override fun onResponse(
                call: Call<Paiement>,
                response: Response<Paiement>
            ) {
                Log.w("response, ",response.toString())
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Paiement>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject create: "+ t.message.toString())
                }
            }
        })
    }

    fun updatePaiement(_id: String, res: Paiement){
        val call = retroInstance.updatePaiement(_id, res)
        call.enqueue(object : Callback<Paiement> {
            override fun onResponse(
                call: Call<Paiement>,
                response: Response<Paiement>
            ) {
                if (response.isSuccessful) {
                    updateLiveData.postValue(response.body())
                }else {
                    updateLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Paiement>, t: Throwable?) {
                updateLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Subject Update: "+ t.message.toString())
                }
            }
        })
    }

    fun deletePaiement(_id: String) {
        val call = retroInstance.deletePaiement(_id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    deleteLiveData.postValue(true) // Successfully deleted
                } else {
                    deleteLiveData.postValue(false) // Handle deletion failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MyApp", "Paiement Delete: "+ t.message.toString())
                deleteLiveData.postValue(false) // Handle network or other failures
            }
        })
    }

}