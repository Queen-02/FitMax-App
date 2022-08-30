package dev.queen.fitmax.ui.login_sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.queen.fitmax.ui.home_and_splash.HomeActivity
import dev.queen.fitmax.R
import dev.queen.fitmax.databinding.ActivitySignUpBinding
import dev.queen.fitmax.models.RegisterRequest
import dev.queen.fitmax.models.RegisterResponse
import dev.queen.fitmax.service.APIClient
import dev.queen.fitmax.service.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //----------setting on click listener--------------//
        binding.tvLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
//            val intent = Intent
            validateSignUp()
        }
    }

    fun validateSignUp(){
        var firstName = binding.etFrstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPass.text.toString()
        var confirm = binding.etConfirmPassword.text.toString()
        var phoneNumber = binding.etPhone.text.toString()
        var error = false


        if (firstName.isBlank()){
            binding.tilFrstName.error = "First name required"
        }

        if (lastName.isBlank()){
            binding.tilLastName.error = "Last name required"
        }

        if (email.isBlank()){
            binding.tlEmail.error = "Email required"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tlEmail.error = "Email is invalid"
        }

        if (password.isBlank()){
            binding.tlPassword.error = "Password required"
        }

        if (phoneNumber.isBlank()){
            binding.tlPhone.error = "Phone number required"
        }

        if (confirm.isBlank()){
            binding.tlConfirmPassword.error = "Confirm password"
        }

        if (password != confirm){
            binding.tlConfirmPassword.error = "Invalid password"

        }
        else{
            Toast.makeText(applicationContext, "Confirmed password", Toast.LENGTH_SHORT).show()
        }

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

        if (!error){
            var registerRequest = RegisterRequest(firstName, lastName, email, password, phoneNumber)
            makeRegistrationRequest(registerRequest)
        }
    }

    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient = APIClient.buildAPIClient(APIInterface::class.java)
        var request = apiClient.registerUser(registerRequest)

        request.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

                    startActivity(Intent(baseContext, LoginActivity::class.java))

                }else{
                    var error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
               Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}