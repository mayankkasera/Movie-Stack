package com.example.moviestack.api.repo.movieInforepo

import com.example.moviestack.api.MovieRequests
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable

interface MovieRepositoryI {
    fun getMovieInfo() : Observable<MovieResponce>

    fun getCredits(): Observable<MovieResponce>

    fun getVideos(): Observable<MovieResponce>

    fun getImages(): Observable<MovieResponce>

    fun getReviews(): Observable<MovieResponce>
}