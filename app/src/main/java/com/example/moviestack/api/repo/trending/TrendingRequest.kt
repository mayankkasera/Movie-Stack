package com.example.moviestack.api.repo.trending

import com.example.moviestack.pojo.MovieList
import com.example.moviestack.pojo.Person
import com.example.moviestack.pojo.SmallItemList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingRequest {

    @GET("trending/movie/day")
    fun getTrendingMovies() : Call<SmallItemList>

    @GET("trending/tv/day")
    fun getTrendingTvShow() : Call<SmallItemList>

    @GET("trending/person/day")
    fun getTrendingPerson() : Call<SmallItemList>

    @GET("trending/person/day")
    fun getTrendingPerson(@Query("page") page :String) : Call<MovieList>

    @GET("trending/movie/day")
    fun getTrendingMovies(@Query("page") page :String) : Call<MovieList>

    @GET("trending/tv/day")
    fun getTrendingTvShow(@Query("page") page :String) : Call<MovieList>

}