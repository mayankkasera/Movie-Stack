package com.example.moviestack.api.repo.person

import com.example.moviestack.api.repo.movieInfo.MovieResponse
import io.reactivex.Observable

interface PersonRepositoryI {
    fun getTaggedImages(id: String): Observable<PersonResponce>

    fun getExternalIds(id: String): Observable<PersonResponce>

}