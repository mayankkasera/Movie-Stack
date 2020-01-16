package com.example.moviestack.api.repo.trending

import com.example.moviestack.pojo.MovieList
import com.example.moviestack.pojo.SmallItemList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingRequest {

    @GET("trending/movie/week")
    fun getTrendingMovies() : Call<SmallItemList>

    @GET("trending/tv/week")
    fun getTrendingTvShow() : Call<SmallItemList>

    @GET("trending/movie/week")
    fun getTrendingMovies(@Query("page") page :String) : Call<MovieList>

    @GET("trending/tv/week")
    fun getTrendingTvShow(@Query("page") page :String) : Call<MovieList>

}