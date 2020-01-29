package com.codeinger.moviestack.pojo


import com.google.gson.annotations.SerializedName

data class TaggedImages(
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
        @SerializedName("aspect_ratio")
        var aspectRatio: Double = 0.0,
        @SerializedName("file_path")
        var filePath: String = "",
        @SerializedName("height")
        var height: Int = 0,
        @SerializedName("iso_639_1")
        var iso6391: Any = Any(),
        @SerializedName("media")
        var media: Media = Media(),
        @SerializedName("media_type")
        var mediaType: String = "",
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        @SerializedName("width")
        var width: Int = 0
    ) {
        data class Media(
            @SerializedName("adult")
            var adult: Boolean = false,
            @SerializedName("backdrop_path")
            var backdropPath: String = "",
            @SerializedName("genre_ids")
            var genreIds: List<Int> = listOf(),
            @SerializedName("id")
            var id: Int = 0,
            @SerializedName("original_language")
            var originalLanguage: String = "",
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
        )
    }
}