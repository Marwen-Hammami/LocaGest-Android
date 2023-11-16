package tn.sim.locagest.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tn.sim.locagest.models.Car

interface FlotteService {

    @POST("car/")
    fun createCar(@Body params: Car): Call<Car>

    @GET("car/")
    fun getCars(): Call<List<Car>>

}