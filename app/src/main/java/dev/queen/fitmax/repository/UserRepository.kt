package dev.queen.fitmax.repository

import dev.queen.fitmax.api.APIClient
import dev.queen.fitmax.api.APIInterface
import dev.queen.fitmax.models.LoginRequest
import dev.queen.fitmax.models.LoginResponse
import dev.queen.fitmax.models.RegisterRequest
import dev.queen.fitmax.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiclient = APIClient.buildAPIClient(APIInterface::class.java)

    //   implementing the user Login network call
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> =
        withContext(Dispatchers.IO) {
            val response = apiclient.loginUser(loginRequest)
            return@withContext response
        }

    //implementing the User Register network call
    suspend fun registerUser(registerRequest: RegisterRequest) : Response<RegisterResponse> =
        withContext(Dispatchers.IO){
            val response = apiclient.registerUser(registerRequest)
            return@withContext response
        }
}