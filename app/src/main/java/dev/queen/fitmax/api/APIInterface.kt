package dev.queen.fitmax.api

import dev.queen.fitmax.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/profile")
    suspend fun userProfile(@Body profileRequest: ProfileRequest): Response<ProfileResponse>
}