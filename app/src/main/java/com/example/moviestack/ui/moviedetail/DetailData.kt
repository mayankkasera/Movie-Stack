package com.example.moviestack.ui.moviedetail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailData (
    var id :String,
    var title :String,
    var type : Type

) : Parcelable {
    @Parcelize
    enum class Type : Parcelable {
        MOVIE,TV_SHOW,PERSON
    }
}