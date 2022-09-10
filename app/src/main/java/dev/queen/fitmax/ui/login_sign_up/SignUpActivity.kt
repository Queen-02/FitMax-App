package dev.queen.fitmax.ui.login_sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.queen.fitmax.ui.home_and_splash.HomeActivity
import dev.queen.fitmax.databinding.ActivitySignUpBinding
import dev.queen.fitmax.models.RegisterRequest
import dev.queen.fitmax.models.RegisterResponse
import dev.queen.fitmax.api.APIClient
import dev.queen.fitmax.api.APIInterface
import dev.queen.fitmax.repository.UserRepository
import dev.queen.fitmax.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel : UserViewModel by viewModels()//instantiating the view model

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

            binding.pbRegister.visibility = View.VISIBLE
            var registerRequest = RegisterRequest(firstName, lastName, email, password, phoneNumber)
            userViewModel.register(registerRequest)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerLiveData.observe(this, Observer { registerResponse ->
            Toast.makeText(baseContext, registerResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        })
        userViewModel.registerErrorLiveData.observe(this, Observer { regError ->
            Toast.makeText(baseContext, regError, Toast.LENGTH_LONG).show()
        })
    }

}