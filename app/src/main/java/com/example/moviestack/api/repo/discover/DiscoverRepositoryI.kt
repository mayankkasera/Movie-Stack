package com.example.moviestack.api.repo.discover

import io.reactivex.Observable

interface DiscoverRepositoryI {
    fun getGenreMovieList(genre: String): Observable<DiscoverResponce>
}