package com.example.moviestack.ui.dashboard.home

import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.dashboard.home.adapter.PersonVerticalAdapter
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
    var upComingList : List<Result>? = null,
    var popularMovieAdapter : SmallItemAdapter? = null,
    var topRateMoviedAdapter : SmallItemAdapter? = null,
    var popularTvShowAdapter : SmallItemAdapter? = null,
    var topRatedTvShowAdapter : SmallItemAdapter? = null,
    var mainSliderAdapter: MainSliderAdapter? =null,
    var personVerticalAdapter : PersonVerticalAdapter?  = null
)