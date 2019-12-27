package com.example.moviestack.ui.dashboard.home

import com.example.moviestack.api.pojo.Trending
import com.example.moviestack.ui.dashboard.home.adapter.TrendingMovieAdapter
import java.util.*

data class HomeState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var trendingMovieAdapter : TrendingMovieAdapter? = null
)

