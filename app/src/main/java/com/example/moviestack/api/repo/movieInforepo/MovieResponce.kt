package com.example.moviestack.api.repo.movieInforepo

data class MovieResponce (
    var type: Type? = null,
    var data: Any? = null
){
    enum class Type{
        CREDIT,MOVIE_INFO,VIDEOS,IMAGES,REVIEW
    }
}