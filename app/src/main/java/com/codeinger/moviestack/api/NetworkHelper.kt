package com.codeinger.moviestack.api

import com.codeinger.moviestack.api.repo.discover.DiscoverRequests
import com.codeinger.moviestack.api.repo.person.PersonRequests
import com.codeinger.moviestack.api.repo.movie.MovieItemRequests
import com.codeinger.moviestack.utils.App
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