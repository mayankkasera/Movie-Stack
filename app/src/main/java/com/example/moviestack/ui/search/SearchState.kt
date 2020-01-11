package com.example.moviestack.ui.search

data class SearchState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null
)

