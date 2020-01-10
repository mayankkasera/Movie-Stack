package com.example.moviestack.api.repo.movieInfo

import com.example.moviestack.api.pojo.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRequests {
    @GET("movie/{id}")
    fun getMovieInfo(@Path("id")id : String) : Call<MovieInfo>

    @GET("movie/{id}/credits")
    fun getCredits(@Path("id")id : String): Call<Credits>

    @GET("movie/{id}/videos")
    fun getVideos(@Path("id")id : String): Call<Videos>

    @GET("movie/{id}/images")
    fun getImages(@Path("id")id : String): Call<Images>

    @GET("movie/{id}/reviews")
    fun getReviews(@Path("id")id : String): Call<Review>

    @GET("movie/{id}/similar")
    fun getSimilar(@Path("id")id : String): Call<MovieList>

    @GET("movie/{id}/similar")
    fun getSimilar(@Path("id")id : String,@Query("page") page :String): Call<MovieList>
}