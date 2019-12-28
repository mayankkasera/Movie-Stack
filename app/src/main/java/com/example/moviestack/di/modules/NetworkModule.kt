package com.example.moviestack.di.modules

import com.example.moviestack.di.intercepter.AuthorizationInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getRetrofit(@Named("name") baseUrl :String,okHttpClient: OkHttpClient) : Retrofit{
         return Retrofit.Builder()
             .baseUrl(baseUrl)
             .client(okHttpClient)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
    }


    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient{
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

}