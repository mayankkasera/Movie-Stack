package com.example.moviestack.pojo


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: List<Result> = listOf(),
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0
) {
    data class Result(
        @SerializedName("author")
        var author: String = "",
        @SerializedName("content")
        var content: String = "",
        @SerializedName("id")
        var id: String = "",
        @SerializedName("url")
        var url: String = ""
    )
}