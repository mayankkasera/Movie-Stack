package com.example.moviestack.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestack.R
import com.example.moviestack.ui.dashboard.MainActivity
import spencerstudios.com.jetdblib.JetDB

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(JetDB.getBoolean(this@SplashActivity, "INTRO_DONE",false)){
            Handler().postDelayed(Runnable {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            },3000)
        }
        else{
            Handler().postDelayed(Runnable {
                val intent = Intent(this@SplashActivity, IntroScreen::class.java)
                startActivity(intent)
                finish()
            },2000)

        }

    }

}
