package tn.sim.locagest.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import tn.sim.locagest.models.Paiement

interface PaiementService {
    data class PaymentIntentResponse(
        @SerializedName("clientSecret") val clientSecret: String
    )

    @GET("pai")
    fun getPaiement(): Call<List<Paiement>>

    @POST("pai")
    fun createPaiement(@Body params: Paiement): Call<Paiement>

    @PUT("pai/{id}")
    fun updatePaiement(@Path("id") id: String, @Body params: Paiement): Call<Paiement>

    @DELETE("pai/{id}")
    fun deletePaiement(@Path("id") id: String): Call<Void>


    @POST("create-intent")
    fun createPaiementIntent(): Call<PaymentIntentResponse>
}