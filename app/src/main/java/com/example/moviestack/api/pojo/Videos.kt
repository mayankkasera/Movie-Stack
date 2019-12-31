package com.example.moviestack.api.pojo


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("results")
    var results: List<Result> = listOf()
) {
    data class Result(
        @SerializedName("id")
        var id: String = "",
        @SerializedName("iso_3166_1")
        var iso31661: String = "",
        @SerializedName("iso_639_1")
        var iso6391: String = "",
        @SerializedName("key")
        var key: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("site")
        var site: String = "",
        @SerializedName("size")
        var size: Int = 0,
        @SerializedName("type")
        var type: String = ""

     ){
        fun getImage():String{
           return "https://img.youtube.com/vi/$key/sddefault.jpg"
        }

    }
}