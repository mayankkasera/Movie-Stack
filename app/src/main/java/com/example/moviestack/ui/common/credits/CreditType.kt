package com.example.moviestack.ui.common.credits

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CreditType (
    var data :String,
    var type :Type

) : Parcelable {
    enum class Type{
        CAST,CREW
    }
}