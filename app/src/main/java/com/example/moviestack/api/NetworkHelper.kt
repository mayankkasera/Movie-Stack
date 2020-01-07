package com.example.moviestack.api

import com.example.moviestack.api.repo.discover.DiscoverRequests
import com.example.moviestack.api.repo.movieInfo.MovieRequests
import com.example.moviestack.api.repo.person.PersonRequests
import com.example.moviestack.api.repo.smallitemlist.SmallItemRequests
import com.example.moviestack.utils.App
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkHelper {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var movieRequests: MovieRequests

    @Inject
    lateinit var discoverRequests: DiscoverRequests

    @Inject
    lateinit var personRequests: PersonRequests

    init {
        App.networkComponent()?.inject(this)
    }

    fun gerRetrofit(): SmallItemRequests? {
        return retrofit.create(SmallItemRequests::class.java)
    }

    fun gerMovieRequests(): MovieRequests? {
        return movieRequests
    }

    fun gerDiscoverRequests(): DiscoverRequests? {
        return discoverRequests
    }

    fun gerPersonRequests(): PersonRequests? {
        return personRequests
    }


}