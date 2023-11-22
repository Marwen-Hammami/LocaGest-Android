package tn.sim.locagest.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.sim.locagest.api.FlotteService

class RetroInstance {
    companion object {

        val BASE_URL = "http://10.0.2.2:9090/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}