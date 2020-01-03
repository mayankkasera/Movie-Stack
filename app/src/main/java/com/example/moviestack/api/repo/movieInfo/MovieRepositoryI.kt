package com.example.moviestack.api.repo.movieInfo

import io.reactivex.Observable

interface MovieRepositoryI {
    fun getMovieInfo() : Observable<MovieResponce>

    fun getCredits(): Observable<MovieResponce>

    fun getVideos(): Observable<MovieResponce>

    fun getImages(): Observable<MovieResponce>

    fun getReviews(): Observable<MovieResponce>

    fun getSimilars(): Observable<MovieResponce>
}