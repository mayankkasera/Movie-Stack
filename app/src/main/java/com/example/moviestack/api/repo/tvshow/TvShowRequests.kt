package com.example.moviestack.api.repo.tvshow

import com.example.moviestack.api.pojo.SmallItemList
import retrofit2.Call
import retrofit2.http.GET

interface TvShowRequests{

    @GET("trending/tv/week")
    fun getTrendingTvShow() : Call<SmallItemList>

}