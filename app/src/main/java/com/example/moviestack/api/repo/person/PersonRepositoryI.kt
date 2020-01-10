package com.example.moviestack.api.repo.person

import com.example.moviestack.api.pojo.MovieCredits
import com.example.moviestack.api.pojo.PersonImages
import com.example.moviestack.api.repo.movieInfo.MovieResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Path

interface PersonRepositoryI {
    fun getTaggedImages(id: String): Observable<PersonResponce>

    fun getExternalIds(id: String): Observable<PersonResponce>

    fun getPersonInfo(id: String): Observable<PersonResponce>

    fun getImages(id : String) : Observable<PersonResponce>

    fun getMovieCredits(id : String) : Observable<PersonResponce>

    fun getTvCredits(id : String) : Observable<PersonResponce>
}