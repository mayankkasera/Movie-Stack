package com.codeinger.moviestack.di.component

import android.content.Context
import com.codeinger.moviestack.api.DataHelper
import com.codeinger.moviestack.api.NetworkHelper
import com.codeinger.moviestack.di.modules.DataModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface NetworkComponent {

    fun getRetrofit() : Retrofit
    fun inject(networkHelper: NetworkHelper)
    fun inject(networkHelper: DataHelper)


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @Named("name") name: String,@BindsInstance @Named("appContext") context: Context): NetworkComponent
    }

}