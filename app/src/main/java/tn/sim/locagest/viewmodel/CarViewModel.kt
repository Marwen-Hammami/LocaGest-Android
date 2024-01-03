package tn.sim.locagest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.sim.locagest.api.FlotteService
import tn.sim.locagest.api.retrofit.RetroInstance
import tn.sim.locagest.models.Car

class CarViewModel: ViewModel() {
    lateinit var recyclerListData: MutableLiveData<List<Car>?>
    lateinit var createLiveData: MutableLiveData<Car?>
//    lateinit var deleteLiveData: MutableLiveData<Boolean>

    init {
        recyclerListData = MutableLiveData()
        createLiveData = MutableLiveData()
//        deleteLiveData = MutableLiveData()
    }

    fun getCarsObservable(): MutableLiveData<List<Car>?> {
        return recyclerListData
    }

    fun getCreateNewMessageObservable(): MutableLiveData<Car?> {
        return createLiveData
    }

//    fun deleteMessageObservable(): MutableLiveData<Boolean> {
//        return deleteLiveData
//    }

    fun getCars() {
        val retroInstance = RetroInstance.getRetroInstance().create(FlotteService::class.java)
        val call = retroInstance.getCars()
        call.enqueue(object : Callback<List<Car>> {
            override fun onResponse(
                call: Call<List<Car>>,
                response: Response<List<Car>>
            ) {
                Log.d("res", response.toString())
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                recyclerListData.postValue(null)
                if (t != null) {
                    Log.e("MyApp", "Message list: " + t.message.toString())
                }
            }
        })
    }
    fun updateCar(immatriculation: String, updatedCar: Car) {
        val retroInstance = RetroInstance.getRetroInstance().create(FlotteService::class.java)
        val call = retroInstance.updateCar(immatriculation, updatedCar)
        call.enqueue(object : Callback<Car> {
            override fun onResponse(call: Call<Car>, response: Response<Car>) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                } else {
                    createLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Car>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Car update error: " + t.message.toString())
                }
            }
        })
    }


    fun createMessage(mess: Car){
        val retroInstance = RetroInstance.getRetroInstance().create(FlotteService::class.java)
        val call = retroInstance.createCar(mess)
        call.enqueue(object : Callback<Car> {
            override fun onResponse(
                call: Call<Car>,
                response: Response<Car>
            ) {
                if (response.isSuccessful) {
                    createLiveData.postValue(response.body())
                }else {
                    createLiveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Car>, t: Throwable?) {
                createLiveData.postValue(null)
                if (t != null) {
                    Log.d("MyApp", "Message create: "+ t.message.toString())
                }
            }
        })
    }

}