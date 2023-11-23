package tn.sim.locagest

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import tn.sim.locagest.api.HistoriqueService
import tn.sim.locagest.api.PaiementService
import tn.sim.locagest.api.ReservationService

class App :Application() {


    companion object {

        private const val TAG = "App"


        val BASE_URL = "http://10.0.2.2:9090/"

        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val ReservationServiceRetroInstance = getRetroInstance().create(ReservationService::class.java)
        val HistoriqueServiceRetroInstance = getRetroInstance().create(HistoriqueService::class.java)
        val PaiementServiceRetroInstance = getRetroInstance().create(PaiementService::class.java)
    }

}