package tn.sim.locagest.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import tn.sim.locagest.models.Car

interface FlotteService {

    @POST("car/")
    fun createCar(@Body params: Car): Call<Car>

    @GET("car/")
    fun getCars(): Call<List<Car>>

    @GET("car/{carId}")
    fun getCarById(@Path("carId") carId: String): Call<Car>

    @PUT("car/{immatriculation}")
    fun updateCar(@Path("immatriculation") immatriculation: String, @Body params: Car): Call<Car>

    @POST("car/delete/{carId}")
    fun deleteCar(@Path("carId") carId: String): Call<Void>

    @GET("car/search")
    fun searchCars(@Query("criteria") criteria: String): Call<List<Car>>
}
