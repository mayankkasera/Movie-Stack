package com.example.moviestack.ui.common.movielist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MovieListType (
    var data :String,
    var type :Type

) : Parcelable {
    enum class Type{
        MOVIE_SEARCH,TV_SEARCH,SMILER,GENRE,MOVIE_CREDITS,TV_CREDITS,TRENDING_MOVIE,TRENDING_TV_SHOW,NOW_PLAYING,UPCOMING,POPULAR,TOP_RATED
    }
}