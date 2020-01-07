package com.example.moviestack.di.modules

import com.example.moviestack.api.repo.discover.DiscoverRequests
import com.example.moviestack.api.repo.movieInfo.MovieRequests
import com.example.moviestack.api.repo.person.PersonRequests
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

    @Provides
    @Singleton
    fun provideMovieRequest(retrofit: Retrofit) : MovieRequests{
        return retrofit.create(MovieRequests::class.java)
    }

    @Provides
    @Singleton
    fun provideDiscoverRequest(retrofit: Retrofit) : DiscoverRequests {
        return retrofit.create(DiscoverRequests::class.java)
    }

    @Provides
    @Singleton
    fun providePersonRequest(retrofit: Retrofit) : PersonRequests {
        return retrofit.create(PersonRequests::class.java)
    }

}