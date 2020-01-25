package com.example.moviestack.api

import com.example.moviestack.api.repo.discover.DiscoverRequests
import com.example.moviestack.api.repo.person.PersonRequests
import com.example.moviestack.api.repo.movie.MovieItemRequests
import com.example.moviestack.utils.App
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkHelper {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var discoverRequests: DiscoverRequests

    @Inject
    lateinit var personRequests: PersonRequests

    init {
        App.networkComponent()?.inject(this)
    }

    fun gerRetrofit(): MovieItemRequests? {
        return retrofit.create(MovieItemRequests::class.java)
    }



    fun gerDiscoverRequests(): DiscoverRequests? {
        return discoverRequests
    }

    fun gerPersonRequests(): PersonRequests? {
        return personRequests
    }


}