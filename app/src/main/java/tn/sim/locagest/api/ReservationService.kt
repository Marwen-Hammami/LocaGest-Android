package tn.sim.locagest.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import tn.sim.locagest.models.Reservation

interface ReservationService {

    data class PaymentIntentResponse(
        @SerializedName("clientSecret") val clientSecret: String
    )

    @GET("res")
    fun getReservations(): Call<List<Reservation>>

    @POST("res")
    fun createReservations(@Body params: Reservation): Call<Reservation>

    @PUT("res/{id}")
    fun updateReservations(@Path("id") id: String, @Body params: Reservation): Call<Reservation>

    @DELETE("res/{id}")
    fun deleteReservations(@Path("id") id: String): Call<Void>


@POST("res/create-intent")
fun createPaiementIntent(): Call<PaymentIntentResponse>



}