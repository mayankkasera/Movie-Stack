package com.codeinger.moviestack.api.repo.trending

import com.codeinger.moviestack.pojo.MovieList
import com.codeinger.moviestack.pojo.SmallItemList
import io.reactivex.Observable

interface TrendingRepositoryI {
    fun getSmallItemsList(type : SmallItemList.Type) : Observable<SmallItemList>

    fun getMoviesList(id : String, type : SmallItemList.Type) : Observable<MovieList>
}