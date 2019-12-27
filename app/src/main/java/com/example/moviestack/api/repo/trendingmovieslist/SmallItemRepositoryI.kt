package com.example.moviestack.api.repo.trendingmovieslist

import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable
import io.reactivex.Single

interface SmallItemRepositoryI {
    fun getTrendingMovies() : Observable<SmallItemList>
}