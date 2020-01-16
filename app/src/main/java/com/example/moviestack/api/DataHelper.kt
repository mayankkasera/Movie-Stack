package com.example.moviestack.api

import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.person.PersonRepositoryI
import com.example.moviestack.api.repo.search.SearchRepositoryI
import com.example.moviestack.api.repo.trending.TrendingRepository
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.utils.App
import javax.inject.Inject

class DataHelper {

    init {
        App.networkComponent()?.inject(this)
    }

    @Inject
    lateinit var discoverRepositoryI: DiscoverRepositoryI


    @Inject
    lateinit var personRepositoryI: PersonRepositoryI

    @Inject
    lateinit var searchRepositoryI: SearchRepositoryI

    @Inject
    lateinit var tvShowRepositoryI: TvShowRepositoryI

    @Inject
    lateinit var trendingRepositoryI: TrendingRepository

    @Inject
    lateinit var movieItemRepositoryI: MovieItemRepositoryI

}