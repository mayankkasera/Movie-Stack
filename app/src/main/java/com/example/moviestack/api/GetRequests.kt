package com.example.moviestack.api

import com.example.moviestack.api.pojo.Trending
import com.example.moviestack.api.pojo.TrendingMovies
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface GetRequests {
    @GET("trending/all/week?api_key=26095c9316646dba756dcbe2a7e602f6")
    fun getTrendingMoviess() : Call<Trending>
}