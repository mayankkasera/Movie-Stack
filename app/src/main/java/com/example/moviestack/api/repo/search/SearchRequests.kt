package com.example.moviestack.api.repo.search

import com.example.moviestack.api.pojo.MovieCredits
import com.example.moviestack.api.pojo.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchRequests {

    @GET("search/movie")
    fun getMovie(@Query("page")page : String,@Query("query")query : String) : Call<Search>

    @GET("search/tv")
    fun getTv(@Query("page")page : String,@Query("query")query : String) : Call<Search>

    @GET("search/person")
    fun getPerson(@Query("page")page : String,@Query("query")query : String) : Call<Search>
}