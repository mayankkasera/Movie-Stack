package com.example.moviestack.ui.dashboard.home

import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter

data class HomeState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var trendingMovieAdapter : SmallItemAdapter? = null,
    var trendingTvShowAdapter : SmallItemAdapter? = null,
    var nowPlayingAdapter : SmallItemAdapter? = null,
    var upComingAdapter : SmallItemAdapter? = null
)

