package com.example.moviestack.api.repo.trendingmovieslist

import com.example.moviestack.api.pojo.Trending
import io.reactivex.Single

interface TrendingMoviesRepositoryI {
    fun getTrendingMovies() : Single<Trending>
}