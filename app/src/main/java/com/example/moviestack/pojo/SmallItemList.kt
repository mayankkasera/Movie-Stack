package com.example.moviestack.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmallItemList(

    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: List<Result> = listOf(),
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0,
    @SerializedName("type")
    var type: Type? = null


) : Parcelable {

    @Parcelize
    enum class Type : Parcelable {
        PERSON,SEARCH_MOVIES,SEARCH_TV,TRENDING_MOVIES,TRENDING_TV_SHOW,
        TRENDING_PERSON,UPCOMING,NOW_PLAYING,POPULAR_MOVIES,TOP_RATED_MOVIES,
        POPULAR_TV_SHOW,TOP_RATED_TV_SHOW,TEST
    }

}