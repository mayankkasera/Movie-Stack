package com.example.moviestack.ui.dashboard.home

import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter
import com.example.moviestack.utils.adapter.MainSliderAdapter

data class HomeState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var trendingMovieAdapter : SmallItemAdapter? = null,
    var trendingTvShowAdapter : SmallItemAdapter? = null,
    var nowPlayingAdapter : SmallItemAdapter? = null,
    var upComingAdapter : SmallItemAdapter? = null,
    var popularAdapter : SmallItemAdapter? = null,
    var topRatedAdapter : SmallItemAdapter? = null,
    var mainSliderAdapter: MainSliderAdapter? =null
)

