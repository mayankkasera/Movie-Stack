package com.example.moviestack.api.repo.movieInfo

data class MovieResponce (
    var type: Type? = null,
    var data: Any? = null
){
    enum class Type{
        CREDIT,MOVIE_INFO,VIDEOS,IMAGES,REVIEW,SIMILAR
    }
}