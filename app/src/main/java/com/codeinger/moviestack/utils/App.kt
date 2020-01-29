package com.codeinger.moviestack.utils

import android.app.Application
import com.codeinger.moviestack.di.component.DaggerRoomComponent
import com.codeinger.moviestack.di.component.NetworkComponent
import com.codeinger.moviestack.di.component.NetworkComponentFactory
import com.codeinger.moviestack.di.component.RoomComponent
import com.facebook.stetho.Stetho
import io.reactivex.plugins.RxJavaPlugins


class App : Application() {

    override fun onCreate() {
        super.onCreate()
       // roomComponent = DaggerRoo.factory().create(NetworkConstants.baseUrl)
        networkComponent = NetworkComponentFactory.create(this)
        roomComponent = DaggerRoomComponent.factory().create(applicationContext)
        Stetho.initializeWithDefaults(this)
        RxJavaPlugins.setErrorHandler { throwable: Throwable? -> }
    }

    companion object {

        private var roomComponent:RoomComponent? = null
        private var networkComponent : NetworkComponent? = null
        fun roomComponent(): RoomComponent? {
            return roomComponent
        }

        fun networkComponent(): NetworkComponent? {
            return networkComponent
        }
    }


}