package com.chihebsapplication.app.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

object ApiService {


    const val BASE_URL =  "http://10.0.2.2:8080/"

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val TOOL_SERVICE: ToolService by lazy {
        retrofit().create(ToolService::class.java)
    }


}