package com.example.moviestack.api.repo.discover

data class DiscoverResponce (
    var type: Type? = null,
    var data: Any? = null
){
    enum class Type{
        GENRE_MOVIES
    }
}