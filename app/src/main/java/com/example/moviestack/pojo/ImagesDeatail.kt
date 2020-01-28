package com.example.moviestack.pojo

import android.os.Parcelable
import com.example.moviestack.api.NetworkConstants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImagesDeatail (
    @SerializedName("aspect_ratio")
    var aspectRatio: Double? = 0.0,
    @SerializedName("file_path")
    var filePath: String? = "",
    @SerializedName("file_paths")
    var filePaths: String? = "https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg",
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("iso_639_1")
    var iso6391: String? = "",
    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int? = 0,
    @SerializedName("width")
    var width: Int? = 0
) : Parcelable{
    fun getImages():String{
        return NetworkConstants.baseImageUrl+filePath
    }
}