package com.example.moviestack.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestack.R
import com.example.moviestack.di.component.NetworkComponent
import com.example.moviestack.utils.App
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}
