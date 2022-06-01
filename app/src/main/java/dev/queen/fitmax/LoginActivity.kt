package dev.queen.fitmax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var tvSign: TextView
    lateinit var etEmail: TextInputEditText
    lateinit var etPwd: TextInputEditText
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPwd: TextInputLayout
    lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvSign = findViewById(R.id.tvSign)
        etEmail = findViewById(R.id.etEmail)
        etPwd = findViewById(R.id.etPWD)

        tilEmail = findViewById(R.id.tlEmail)
        tilPwd = findViewById(R.id.tlPWD)

        btnLogin = findViewById(R.id.btnLogIn)

        tvSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            validate()
        }
    }

    fun validate(){
        var email = etEmail.text.toString()
        var pwd = etPwd.text.toString()

        if (email.isBlank()){
            tilEmail.error = "@string/errorInEmail"
        }

        if (pwd.isBlank()){
            tilPwd.error = "@string/errorInPassword"
        }
    }
}