package com.example.moviestack.api.repo.tvshow

import com.example.moviestack.api.repo.movie.MovieResponse
import com.example.moviestack.pojo.MovieList
import com.example.moviestack.pojo.SmallItemList
import io.reactivex.Observable

interface TvShowRepositoryI {



    fun getTvShowInfo(id: String): Observable<MovieResponse>

    fun getCredits(id: String): Observable<MovieResponse>

    fun getVideos(id: String): Observable<MovieResponse>

    fun getImages(id: String): Observable<MovieResponse>

    fun getReviews(id: String): Observable<MovieResponse>

    fun getSimilars(id: String): Observable<MovieResponse>

    fun getSimilars(id: String,page :String): Observable<MovieResponse>

    fun getSmallItemsList(type : SmallItemList.Type): Observable<SmallItemList>

    fun getTvShowList(id : String, type : SmallItemList.Type) : Observable<MovieList>

}