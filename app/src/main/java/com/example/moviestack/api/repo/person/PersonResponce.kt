package com.example.moviestack.api.repo.person



data class PersonResponce (
    var type: Type? = null,
    var data: Any? = null
){
    enum class Type{
        TAGGED_IMAGES,EXTERNAL_IDS
    }
}