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
        SMILER,GENRE
    }
}