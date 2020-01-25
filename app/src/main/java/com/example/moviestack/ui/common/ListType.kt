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
        TV_CREDITS,TRENDING_MOVIE,TRENDING_TV_SHOW,TRENDING_PERSON,NOW_PLAYING,
        UPCOMING,POPULAR,POPULAR_TV_SHOW,TOP_RATED,TOP_RATED_TV_SHOW,CAST,CREW,BOOKMARK_MOVIE,BOOKMARK_TV_SHOW,BOOKMARK_PERSON,
        MY_LIST_TV_SHOW,MY_LIST_MOVIE
    }
}