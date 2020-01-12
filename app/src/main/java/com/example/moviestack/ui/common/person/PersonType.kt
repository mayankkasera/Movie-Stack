package com.example.moviestack.ui.common.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PersonType (
    var data :String,
    var type :Type

) : Parcelable {
    enum class Type{
        CAST,CREW
    }
}