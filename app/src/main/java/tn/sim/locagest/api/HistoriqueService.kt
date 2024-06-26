package tn.sim.locagest.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import tn.sim.locagest.models.Historique

interface HistoriqueService {
    @GET("res")
    fun getHistoriques(): Call<List<Historique>>

    @POST("res")
    fun createHistoriques(@Body params: Historique): Call<Historique>

    @PUT("res/{id}")
    fun updateHistoriques(@Path("id") id: String, @Body params: Historique): Call<Historique>

    @DELETE("res/{id}")
    fun deleteHistoriques(@Path("id") id: String): Call<Void>
}