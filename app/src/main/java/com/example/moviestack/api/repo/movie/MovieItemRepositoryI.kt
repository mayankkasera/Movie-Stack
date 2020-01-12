package com.example.moviestack.api.repo.movie

import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable

interface MovieItemRepositoryI {

    fun getSmallItemsList(type : SmallItemList.Type) : Observable<SmallItemList>

    fun getMoviesList(id : String, type : SmallItemList.Type) : Observable<MovieList>

}