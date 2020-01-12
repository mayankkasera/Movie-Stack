package com.example.moviestack.api.repo.movie

import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.pojo.SmallItemList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieItemRequests {


    @GET("movie/now_playing")
    fun getNowPlaying() : Call<SmallItemList>

    @GET("movie/upcoming")
    fun getUpcoming() : Call<SmallItemList>

    @GET("movie/popular")
    fun getPopular() : Call<SmallItemList>

    @GET("movie/top_rated")
    fun getTopRated() : Call<SmallItemList>




    @GET("movie/now_playing")
    fun getNowPlaying(@Query("page") page :String) : Call<MovieList>

    @GET("movie/upcoming")
    fun getUpcoming(@Query("page") page :String) : Call<MovieList>

    @GET("movie/popular")
    fun getPopular(@Query("page") page :String) : Call<MovieList>

    @GET("movie/top_rated")
    fun getTopRated(@Query("page") page :String) : Call<MovieList>
}