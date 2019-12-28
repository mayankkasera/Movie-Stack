package com.example.moviestack.api

import com.example.moviestack.api.pojo.SmallItemList
import retrofit2.Call
import retrofit2.http.GET

interface GetRequests {
    @GET("trending/all/week")
    fun getTrendingMoviess() : Call<SmallItemList>

    @GET("trending/tv/week")
    fun getTrendingTvShow() : Call<SmallItemList>

    @GET("movie/now_playing")
    fun getNowPlaying() : Call<SmallItemList>

    @GET("movie/upcoming")
    fun getUpcoming() : Call<SmallItemList>

    @GET("movie/popular")
    fun getPopular() : Call<SmallItemList>

    @GET("movie/top_rated")
    fun getTopRated() : Call<SmallItemList>
}