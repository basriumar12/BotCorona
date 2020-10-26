package com.basri.newsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiRetrofit {
     fun getClient(): Retrofit {

           return Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    val apiService: ApiService = getClient().create(ApiService::class.java)
}