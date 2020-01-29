package com.codeinger.moviestack.api.repo.discover

import io.reactivex.Observable

interface DiscoverRepositoryI {
    fun getGenreMovieList(genre: String): Observable<DiscoverResponce>

    fun getGenreMovieList(genre: String,page :String): Observable<DiscoverResponce>
}