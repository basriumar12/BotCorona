package com.basri.newsapp.network

import com.bas.botinfo.data.entities.cor.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("summary")
    fun getData(): Call<ResponseData>

}