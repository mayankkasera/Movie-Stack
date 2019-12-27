package com.example.moviestack.api

import com.example.moviestack.api.pojo.SmallItemList
import retrofit2.Call
import retrofit2.http.GET

interface GetRequests {
    @GET("trending/all/week?api_key=26095c9316646dba756dcbe2a7e602f6")
    fun getTrendingMoviess() : Call<SmallItemList>

    @GET("trending/tv/week?api_key=26095c9316646dba756dcbe2a7e602f6")
    fun getTrendingTvShow() : Call<SmallItemList>

    @GET("movie/now_playing?api_key=26095c9316646dba756dcbe2a7e602f6")
    fun getNowPlaying() : Call<SmallItemList>

    @GET("movie/upcoming?api_key=26095c9316646dba756dcbe2a7e602f6")
    fun getUpcoming() : Call<SmallItemList>

    @GET("/movie/popular?api_key=26095c9316646dba756dcbe2a7e602f6")
    fun getPopular() : Call<SmallItemList>

    @GET("/movie/top_rated?api_key=26095c9316646dba756dcbe2a7e602f6")
    fun getTopRated() : Call<SmallItemList>
}