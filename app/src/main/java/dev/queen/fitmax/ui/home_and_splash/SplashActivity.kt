package dev.queen.fitmax.ui.home_and_splash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.queen.fitmax.ui.login_sign_up.LoginActivity

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("FITMAX_PREFS", MODE_PRIVATE)

        //reading data from the shared preferences
        var access_token = sharedPreferences.getString("ACCESS_TOKEN", "").toString()
        if (access_token.isBlank()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }else{
            startActivity(Intent(baseContext, HomeActivity::class.java))
        }
        finish()
    }
}