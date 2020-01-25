package com.example.tvstack.api.repo.tvshow

import com.example.moviestack.pojo.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowRequests{

    @GET("tv/{id}")
    fun getTvShowInfo(@Path("id")id : String) : Call<MovieInfo>

    @GET("tv/{id}/credits")
    fun getCredits(@Path("id")id : String): Call<Credits>

    @GET("tv/{id}/videos")
    fun getVideos(@Path("id")id : String): Call<Videos>

    @GET("tv/{id}/images")
    fun getImages(@Path("id")id : String): Call<Images>

    @GET("tv/{id}/reviews")
    fun getReviews(@Path("id")id : String): Call<Review>

    @GET("tv/{id}/similar")
    fun getSimilar(@Path("id")id : String): Call<MovieList>

    @GET("tv/{id}/similar")
    fun getSimilar(@Path("id")id : String, @Query("page") page :String): Call<MovieList>



    @GET("tv/popular")
    fun getPopularTvShow() : Call<SmallItemList>


    @GET("tv/top_rated")
    fun getTopRatedTvShow() : Call<SmallItemList>


    @GET("tv/popular")
    fun getPopular(@Query("page") page :String) : Call<MovieList>

    @GET("tv/top_rated")
    fun getTopRated(@Query("page") page :String) : Call<MovieList>

}