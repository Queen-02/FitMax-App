package dev.queen.fitmax.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://192.81.215.35")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildAPIClient(apiInterface: Class<T>): T {
        return retrofit.create(apiInterface)
    }

}