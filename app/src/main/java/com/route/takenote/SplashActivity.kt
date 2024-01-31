package com.route.takenote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler(mainLooper).postDelayed(object :Runnable{
            override fun run() {
                makeIntent()
            }

        },2000)
    }

    private fun makeIntent() {
        val intent = Intent(this@SplashActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}