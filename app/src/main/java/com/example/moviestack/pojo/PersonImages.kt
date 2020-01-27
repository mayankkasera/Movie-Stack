package com.example.moviestack.pojo


import com.example.moviestack.utils.NetworkConstants
import com.google.gson.annotations.SerializedName

data class PersonImages(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("profiles")
    var profiles: List<ImagesDeatail> = listOf()
) {

}