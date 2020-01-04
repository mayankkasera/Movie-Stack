package com.example.moviestack.api.repo.movieInfo

data class MovieResponse (
    var type: Type? = null,
    var data: Any? = null
){
    enum class Type{
        CREDIT,MOVIE_INFO,VIDEOS,IMAGES,REVIEW,SIMILAR
    }
}