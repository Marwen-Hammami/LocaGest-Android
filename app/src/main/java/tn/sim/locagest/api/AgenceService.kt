package tn.sim.locagest.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import tn.sim.locagest.models.Agence

interface AgenceService {

    @GET("agence")
    fun getAll(): Call<List<Agence>>

    @POST("agence")
    fun create(@Body params: Agence): Call<Agence>

    @PUT("agence/{id}")
    fun update(@Path("id") id: String, @Body params: Agence): Call<Agence>

    @DELETE("agence/{id}")
    fun delete(@Path("id") id: String): Call<Void>
}