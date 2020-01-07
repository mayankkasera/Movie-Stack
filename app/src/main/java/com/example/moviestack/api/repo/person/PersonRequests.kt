package com.example.moviestack.api.repo.person

import com.example.moviestack.api.pojo.ExternalIds
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.pojo.TaggedImages
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonRequests {
    @GET("person/{id}/tagged_images")
    fun getTaggedImages(@Path("id")id : String) : Call<TaggedImages>

    @GET("person/{id}/external_ids")
    fun getExternalIds(@Path("id")id : String) : Call<ExternalIds>
}