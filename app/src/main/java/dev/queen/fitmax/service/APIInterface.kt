package dev.queen.fitmax.service

import dev.queen.fitmax.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("/profile")
    fun userProfile(@Body profileRequest: ProfileRequest) : Call<ProfileResponse>
}