package com.example.moviestack.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getRetrofit(@Named("name") baseUrl :String ) : Retrofit{
         return Retrofit.Builder()
             .baseUrl(baseUrl)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
    }

}