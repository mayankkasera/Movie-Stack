package com.example.moviestack.api.repo.person

import com.example.moviestack.api.pojo.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonRequests {
    @GET("person/{id}/tagged_images")
    fun getTaggedImages(@Path("id")id : String) : Call<TaggedImages>

    @GET("person/{id}/external_ids")
    fun getExternalIds(@Path("id")id : String) : Call<ExternalIds>

    @GET("person/{id}")
    fun getPersonInfo(@Path("id")id : String) : Call<PersonInfo>

    @GET("person/{id}/images")
    fun getImages(@Path("id")id : String) : Call<PersonImages>

    @GET("person/{id}/movie_credits")
    fun getMovieCredits(@Path("id")id : String) : Call<MovieCredits>

    @GET("person/{id}/tv_credits")
    fun getTvCredits(@Path("id")id : String) : Call<MovieCredits>
}