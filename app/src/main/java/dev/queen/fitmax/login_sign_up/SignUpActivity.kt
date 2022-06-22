package dev.queen.fitmax.login_sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.queen.fitmax.home_and_splash.HomeActivity
import dev.queen.fitmax.R
import dev.queen.fitmax.databinding.ActivitySignUpBinding

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
        var fName = binding.etFrstName.text.toString()
        var lName = binding.etLastName.text.toString()
        var email = binding.etEmail.text.toString()
        var pwd = binding.etPassword.text.toString()
        var confirm = binding.etConfirmPassword.text.toString()


        if (fName.isBlank()){
            binding.tilFrstName.error = "First name required"
        }

        if (lName.isBlank()){
            binding.tilLastName.error = "Last name required"
        }

        if (email.isBlank()){
            binding.tlEmail.error = "Email required"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tlEmail.error = "Email is invalid"
        }

        if (pwd.isBlank()){
            binding.tlPassword.error = "Password required"
        }

        if (confirm.isBlank()){
            binding.tlConfirmPassword.error = "Confirm password"
        }

        if (pwd != confirm){
            binding.tlConfirmPassword.error = "Invalid password"

        }
        else{
            Toast.makeText(applicationContext, "Confirmed password", Toast.LENGTH_SHORT).show()
        }

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}