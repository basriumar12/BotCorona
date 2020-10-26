package com.basri.coroinfo.api

import com.basri.newsapp.network.ApiService

class ApiHelper(private val apiService: ApiService) {

    suspend fun getData() = apiService.getData()
}