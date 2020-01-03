package com.example.moviestack.api

import com.example.moviestack.api.repo.discover.DiscoverRequests
import com.example.moviestack.api.repo.movieInfo.MovieRequests
import com.example.moviestack.api.repo.smallitemlist.SmallItemRequests
import com.example.moviestack.utils.App
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkHelper {

    @Inject
    lateinit var retrofit : Retrofit

    init {
        App.networkComponent()?.inject(this)
    }

    fun gerRetrofit() : SmallItemRequests?{
        return  retrofit.create(SmallItemRequests::class.java)
    }

    fun gerMovieRequests() : MovieRequests?{
        return  retrofit.create(MovieRequests::class.java)
    }

    fun gerDiscoverRequests() : DiscoverRequests?{
        return  retrofit.create(DiscoverRequests::class.java)
    }



}