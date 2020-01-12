package com.example.moviestack.api.repo.movieInfo

import io.reactivex.Observable

interface MovieRepositoryI {
    fun getMovieInfo(id: String): Observable<MovieResponse>

    fun getCredits(id: String): Observable<MovieResponse>

    fun getVideos(id: String): Observable<MovieResponse>

    fun getImages(id: String): Observable<MovieResponse>

    fun getReviews(id: String): Observable<MovieResponse>

    fun getSimilars(id: String): Observable<MovieResponse>

    fun getSimilars(id: String,page :String): Observable<MovieResponse>


}