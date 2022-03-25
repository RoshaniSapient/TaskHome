package com.example.hometask.Service

import com.example.hometask.model.stockList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/market/v2/get-summary")
    suspend fun getStckList(@Path("id") id: Int): List<stockList>
}