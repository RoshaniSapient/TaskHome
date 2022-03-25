package com.example.hometask.reporsitory

import com.example.hometask.Service.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StockConfig {

    private const val BASE_URL = "https://english.api.rakuten.net/apidojo/api/yh-finance"

    fun ApiService(): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
}