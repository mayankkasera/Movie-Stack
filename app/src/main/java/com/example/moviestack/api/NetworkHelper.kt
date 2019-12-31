package com.example.moviestack.api

import com.example.moviestack.di.component.NetworkComponent
import com.example.moviestack.utils.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkHelper {

    @Inject
    lateinit var retrofit : Retrofit

    init {
        App.networkComponent()?.inject(this)
    }

    fun gerRetrofit() : GetRequests?{
        return  retrofit.create(GetRequests::class.java)
    }

    fun gerMovieRequests() : MovieRequests?{
        return  retrofit.create(MovieRequests::class.java)
    }



}