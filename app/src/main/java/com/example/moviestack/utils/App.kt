package com.example.moviestack.utils

import android.app.Application
import com.example.moviestack.di.component.NetworkComponent
import com.example.moviestack.di.component.NetworkComponentFactory
import com.facebook.stetho.Stetho

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        networkComponent = NetworkComponentFactory.create()
        Stetho.initializeWithDefaults(this)
    }

    companion object {

        private var networkComponent : NetworkComponent? = null

        fun networkComponent(): NetworkComponent? {
            return networkComponent
        }
    }


}