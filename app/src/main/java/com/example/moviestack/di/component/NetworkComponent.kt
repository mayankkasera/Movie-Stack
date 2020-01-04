package com.example.moviestack.di.component

import com.example.moviestack.api.DataHelper
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.di.modules.DataModule
import com.example.moviestack.ui.dashboard.MainActivity
import com.example.moviestack.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface NetworkComponent {

    fun getRetrofit() : Retrofit

    fun inject(networkHelper: NetworkHelper) : Unit
    fun inject(networkHelper: DataHelper)


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @Named("name") name: String): NetworkComponent
    }

}