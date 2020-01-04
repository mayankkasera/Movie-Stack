package com.example.moviestack.api

import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.utils.App
import javax.inject.Inject

class DataHelper {

    init {
        App.networkComponent()?.inject(this)
    }

    @Inject
    lateinit var discoverRepositoryI: DiscoverRepositoryI

    @Inject
    lateinit var movieRepositoryI: MovieRepositoryI
}