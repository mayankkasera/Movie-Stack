package com.example.moviestack.api.pojo


import com.google.gson.annotations.SerializedName

data class MovieCredits(
    @SerializedName("person")
    var cast: List<Result> = listOf(),
    @SerializedName("crew")
    var crew: List<Result> = listOf(),
    @SerializedName("id")
    var id: Int = 0
)