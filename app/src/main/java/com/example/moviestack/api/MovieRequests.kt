package com.example.moviestack.api

import com.example.moviestack.api.pojo.*
import retrofit2.Call
import retrofit2.http.GET

interface MovieRequests {
    @GET("movie/475557")
    fun getMovieInfo() : Call<MovieInfo>

    @GET("movie/475557/credits")
    fun getCredits(): Call<Credits>

    @GET("movie/475557/videos")
    fun getVideos(): Call<Videos>

    @GET("movie/475557/images")
    fun getImages(): Call<Images>
}