package com.codeinger.moviestack.api.repo.person



data class PersonResponce (
    var type: Type? = null,
    var data: Any? = null
){
    enum class Type{
        TAGGED_IMAGES,EXTERNAL_IDS,PERSON_INFO,PERSON_IMAGES,MOVIE_CREDITS,POPULAR
    }
}