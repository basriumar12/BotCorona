package com.basri.coroinfo.api

class MainRepository (private val apiHelper: ApiHelper) {
    suspend fun getData() = apiHelper.getData()
}