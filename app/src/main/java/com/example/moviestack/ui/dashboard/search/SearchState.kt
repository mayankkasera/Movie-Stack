package com.example.moviestack.ui.dashboard.search

data class SearchState(
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null
){
    enum class Type{
        MOVIE,TV_SHOW
    }
}