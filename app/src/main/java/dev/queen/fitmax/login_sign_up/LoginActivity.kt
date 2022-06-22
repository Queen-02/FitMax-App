package dev.queen.fitmax.login_sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.queen.fitmax.home_and_splash.HomeActivity
import dev.queen.fitmax.R
import dev.queen.fitmax.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogIn.setOnClickListener {
            validate()
        }
    }

    fun validate(){
        var email = binding.etEmail.text.toString()
        var pwd = binding.etPWD.text.toString()

        if (email.isBlank()){
            binding.tlEmail.error = "Email required"
        }

        if (pwd.isBlank()){
            binding.tlPWD.error = "Password required"
        }

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}