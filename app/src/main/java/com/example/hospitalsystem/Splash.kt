package com.example.hospitalsystem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity



class Splash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_srceen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@Splash, MainActivity::class.java))
            finish()
        }, 3000)
    }


}