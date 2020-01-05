package com.example.moviestack.api.pojo


import com.example.moviestack.utils.NetworkConstants
import com.google.gson.annotations.SerializedName

data class Credits(
    @SerializedName("cast")
    var cast: List<Cast>,
    @SerializedName("crew")
    var crew: List<Crew>,
    @SerializedName("id")
    var id: Int
) {
    data class Cast(
        @SerializedName("cast_id")
        var castId: Int,
        @SerializedName("character")
        var character: String,
        @SerializedName("credit_id")
        var creditId: String,
        @SerializedName("gender")
        var gender: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("order")
        var order: Int,
        @SerializedName("profile_path")
        var profilePath: String
    ){
        fun getImage():String{
            return NetworkConstants.baseImageUrl+profilePath
        }
    }

    data class Crew(
        @SerializedName("credit_id")
        var creditId: String,
        @SerializedName("department")
        var department: String,
        @SerializedName("gender")
        var gender: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("job")
        var job: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("profile_path")
        var profilePath: String
    ){
        fun getImage():String{
            return NetworkConstants.baseImageUrl+profilePath
        }
    }
}