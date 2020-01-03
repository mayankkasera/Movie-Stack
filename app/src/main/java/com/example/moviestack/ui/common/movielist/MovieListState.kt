package com.example.moviestack.ui.common.movielist

import com.example.moviestack.ui.common.movielist.adapter.MovieListAdapter

data class MovieListState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var movieListAdapter: MovieListAdapter? = null
)

