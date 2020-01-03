package com.example.moviestack.api.repo.discover

import com.example.moviestack.api.repo.movieInfo.MovieResponce
import io.reactivex.Observable

interface DiscoverRepositoryI {
    fun getGenreMovieList(): Observable<DiscoverResponce>
}