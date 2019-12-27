package com.example.moviestack.api.pojo


import com.example.moviestack.utils.NetworkConstants
import com.google.gson.annotations.SerializedName

data class SmallItemList(

    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: List<Result> = listOf(),
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0,
    @SerializedName("type")
    var type: Type? = null


) {

    data class Result(
        @SerializedName("adult")
        var adult: Boolean = false,
        @SerializedName("backdrop_path")
        var backdropPath: String = "",
        @SerializedName("first_air_date")
        var firstAirDate: String = "",
        @SerializedName("genre_ids")
        var genreIds: List<Int> = listOf(),
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("media_type")
        var mediaType: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("origin_country")
        var originCountry: List<String> = listOf(),
        @SerializedName("original_language")
        var originalLanguage: String = "",
        @SerializedName("original_name")
        var originalName: String = "",
        @SerializedName("original_title")
        var originalTitle: String = "",
        @SerializedName("overview")
        var overview: String = "",
        @SerializedName("popularity")
        var popularity: Double = 0.0,
        @SerializedName("poster_path")
        var posterPath: String = "",
        @SerializedName("release_date")
        var releaseDate: String = "",
        @SerializedName("title")
        var title: String = "",
        @SerializedName("video")
        var video: Boolean = false,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0
    ){
        fun hasTitle():Boolean{
            if(title.equals(""))
                return false
            else
                return true
        }

        fun getPoster():String{
            return NetworkConstants.baseImageUrl+posterPath
        }
    }

    enum class Type{
        TRENDING_MOVIES,TRENDING_TV_SHOW,UPCOMING,NOW_PLAYING
    }

}