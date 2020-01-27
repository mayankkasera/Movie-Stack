package com.example.moviestack.pojo


import android.util.Log
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.utils.NetworkConstants
import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("backdrops")
    var backdrops: List<ImagesDeatail> = listOf(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("posters")
    var posters: List<ImagesDeatail> = listOf()
) {

    fun getBackdropImage():String{
        if(backdrops.size>0){
            return NetworkConstants.baseImageUrl500+backdrops.get(0).filePath
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