package com.example.moviestack.ui.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListType (
    var data :String,
    var type : Type

) : Parcelable {
    enum class Type{
        POPULAR_PERSON,PERSON_SEARCH,MOVIE_SEARCH,TV_SEARCH,SMILER,GENRE,MOVIE_CREDITS,
        TV_CREDITS,TRENDING_MOVIE,TRENDING_TV_SHOW,NOW_PLAYING,
        UPCOMING,POPULAR,TOP_RATED,CAST,CREW,
    }
}