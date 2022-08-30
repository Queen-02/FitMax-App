package dev.queen.fitmax.service

import dev.queen.fitmax.models.RegisterRequest
import dev.queen.fitmax.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}