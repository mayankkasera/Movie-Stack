package com.example.moviestack.pojo


import com.example.moviestack.utils.NetworkConstants
import com.google.gson.annotations.SerializedName

data class PersonImages(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("profiles")
    var profiles: List<Profile> = listOf()
) {
    data class Profile(
        @SerializedName("aspect_ratio")
        var aspectRatio: Double = 0.0,
        @SerializedName("file_path")
        var filePath: String = "",
        @SerializedName("height")
        var height: Int = 0,
        @SerializedName("iso_639_1")
        var iso6391: Any = Any(),
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        @SerializedName("width")
        var width: Int = 0
    ){
        fun getImages():String{
            return NetworkConstants.baseImageUrl+filePath
        }
    }
}