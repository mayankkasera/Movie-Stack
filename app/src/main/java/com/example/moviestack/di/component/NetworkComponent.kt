package com.example.moviestack.di.component

import com.example.moviestack.ui.activity.MainActivity
import com.example.moviestack.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun getRetrofit() : Retrofit

    fun inject(mainActivity: MainActivity) : Unit


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @Named("name") name: String): NetworkComponent
    }

}