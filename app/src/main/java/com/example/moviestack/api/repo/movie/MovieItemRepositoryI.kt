package com.example.moviestack.api.repo.movie

import com.example.moviestack.api.repo.movie.MovieResponse
import com.example.moviestack.pojo.MovieList
import com.example.moviestack.pojo.SmallItemList
import io.reactivex.Observable

interface MovieItemRepositoryI {

    fun getSmallItemsList(type : SmallItemList.Type) : Observable<SmallItemList>

    fun getMoviesList(id : String, type : SmallItemList.Type) : Observable<MovieList>

    fun getMovieInfo(id: String): Observable<MovieResponse>

    fun getCredits(id: String): Observable<MovieResponse>

    fun getVideos(id: String): Observable<MovieResponse>

    fun getImages(id: String): Observable<MovieResponse>

    fun getReviews(id: String): Observable<MovieResponse>

    fun getSimilars(id: String): Observable<MovieResponse>

    fun getSimilars(id: String,page :String): Observable<MovieResponse>

}