package com.example.moviestack.api.repo.discover

import com.example.moviestack.api.pojo.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DiscoverRequests {
    @GET("discover/movie")
    fun getGenreMovieList(@Query("with_genres")with_genres :String,@Query("page") page :String): Call<MovieList>

    @GET("discover/movie")
    fun getGenreMovieList(@Query("with_genres")with_genres :String): Call<MovieList>


}
