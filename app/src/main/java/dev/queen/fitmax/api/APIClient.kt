package dev.queen.fitmax.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dev.queen.fitmax.FitMax
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(FitMax.appContext))
        .build()
    var retrofit = Retrofit.Builder()
        .baseUrl("http://192.81.215.35")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

    fun <T> buildAPIClient(apiInterface: Class<T>): T {
        return retrofit.create(apiInterface)
    }

}