package dev.queen.fitmax.ui.login_sign_up

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.queen.fitmax.ui.home_and_splash.HomeActivity
import dev.queen.fitmax.R
import dev.queen.fitmax.databinding.ActivityLoginBinding
import dev.queen.fitmax.models.LoginRequest
import dev.queen.fitmax.models.LoginResponse
import dev.queen.fitmax.service.APIClient
import dev.queen.fitmax.service.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("FITMAX_PREFS", MODE_PRIVATE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogIn.setOnClickListener {
            validateLogin()
        }
    }

    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPWD.text.toString()
        var error = false

        if (email.isBlank()) {
            error = true
            binding.tlEmail.error = "Email required"
        }

        if (password.isBlank()) {
            error = true
            binding.tlPWD.error = "Password required"
        }

        if (!error) {
            var loginRequest = LoginRequest(email, password)
            makeLoginRequest(loginRequest)

        }
    }

    fun makeLoginRequest(loginRequest: LoginRequest) {
        var apiClient = APIClient.buildAPIClient(APIInterface::class.java)
        var request = apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Toast.makeText(baseContext, loginResponse.message, Toast.LENGTH_LONG).show()
                        persistLoginDetails(loginResponse)
                        startActivity(Intent(baseContext, HomeActivity::class.java))
                    }
                } else {
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    fun persistLoginDetails(loginResponse: LoginResponse) {
        val editor = sharedPreferences.edit()
        editor.putString("USER_ID", loginResponse.user_id)
        editor.putString("ACCESS_TOKEN", loginResponse.access_token)
        editor.putString("PROFILE_ID", loginResponse.profile_id)
        editor.apply()

    }
}