package dev.queen.fitmax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.quicksettings.Tile
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
        //--------TEXT VIEW ------------//
    lateinit var tvLogin : TextView
        //-------TextInputEditText----------//
    lateinit var etFrstNme: TextInputEditText
    lateinit var etLastName: TextInputEditText
    lateinit var etEmil: TextInputEditText
    lateinit var etPassword: TextInputEditText
    lateinit var etConfrimPassword: TextInputEditText

        //----------TextInputLayout-----------//
    lateinit var tilFirstName: TextInputLayout
    lateinit var tilLastNme: TextInputLayout
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword: TextInputLayout
    lateinit var tilConfirm: TextInputLayout

        //-----------BUTTON----------//
    lateinit var btnSignUp: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //-------- casting the TEXT VIEW ------------//
        tvLogin = findViewById(R.id.tvLogIn)

        //-------- casting the Text input edit texts ------------//
        etFrstNme = findViewById(R.id.etFrstName)
        etLastName = findViewById(R.id.etLastName)
        etEmil = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfrimPassword = findViewById(R.id.etConfirmPassword)

        //-------- casting the Text input layout ------------//
        tilFirstName = findViewById(R.id.tilFrstName)
        tilLastNme = findViewById(R.id.tilLastName)
        tilEmail = findViewById(R.id.tlEmail)
        tilPassword = findViewById(R.id.tlPassword)
        tilConfirm = findViewById(R.id.tlConfirmPassword)

        //-------- casting the Button ------------//
        btnSignUp = findViewById(R.id.btnSignUp)

        //----------setting on click listener--------------//
        tvLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
//            val intent = Intent
            validateSignUp()
        }
    }

    fun validateSignUp(){
        var fName = etFrstNme.text.toString()
        var lName = etLastName.text.toString()
        var email = etEmil.text.toString()
        var pwd = etPassword.text.toString()

        if (fName.isBlank()){
            tilFirstName.error = "First name required"
        }

        if (lName.isBlank()){
            tilLastNme.error = "Last name required"
        }

        if (email.isBlank()){
            tilEmail.error = "Email required"
        }

        if (pwd.isBlank()){
            tilPassword.error = "Password required"
        }
    }
}