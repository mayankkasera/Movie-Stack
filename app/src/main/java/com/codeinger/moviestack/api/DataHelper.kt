package com.codeinger.moviestack.api

import com.codeinger.moviestack.api.repo.discover.DiscoverRepositoryI
import com.codeinger.moviestack.api.repo.movie.MovieItemRepositoryI
import com.codeinger.moviestack.api.repo.person.PersonRepositoryI
import com.codeinger.moviestack.api.repo.search.SearchRepositoryI
import com.codeinger.moviestack.api.repo.trending.TrendingRepository
import com.codeinger.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.codeinger.moviestack.utils.App
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