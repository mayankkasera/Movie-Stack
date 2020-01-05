package com.example.moviestack.api.pojo


import android.util.Log
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.utils.NetworkConstants
import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("backdrops")
    var backdrops: List<Backdrop> = listOf(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("posters")
    var posters: List<Poster> = listOf()
) {
    data class Backdrop(
        @SerializedName("aspect_ratio")
        var aspectRatio: Double = 0.0,
        @SerializedName("file_path")
        var filePath: String = "",
        @SerializedName("file_paths")
        var filePaths: String = "https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg",
        @SerializedName("height")
        var height: Int = 0,
        @SerializedName("iso_639_1")
        var iso6391: String = "",
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        @SerializedName("width")
        var width: Int = 0

    )

    data class Poster(
        @SerializedName("aspect_ratio")
        var aspectRatio: Double = 0.0,
        @SerializedName("file_path")
        var filePath: String = "",
        @SerializedName("height")
        var height: Int = 0,
        @SerializedName("iso_639_1")
        var iso6391: String = "",
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        @SerializedName("width")
        var width: Int = 0,
        @SerializedName("file_paths")
        var filePaths: String = "https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg"
    )

    fun getBackdropImage():String{
        if(backdrops.size>0){
            return NetworkConstants.baseImageUrl+backdrops.get(0).filePath
        }else{
            return ""
        }
    }

    fun getPoster():String{
        if(posters.size>0){
            return NetworkConstants.baseImageUrl+posters.get(0).filePath
        }else{
            return ""
        }
    }

    fun getBackdropSize():String{
        if(backdrops.size>0){
            return "${backdrops.size} Backdrops"
        }else{
            return ""
        }
    }

    fun getPosterSize():String{
        if(posters.size>0){
            return "${posters.size} Posters"
        }else{
            return ""
        }
    }
}