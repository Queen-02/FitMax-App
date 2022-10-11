package dev.queen.fitmax

import android.app.Application
import android.content.Context

class FitMax: Application() {

    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}