package com.example.moviestack.ui.moviedetail

import com.example.moviestack.api.pojo.Videos

data class MovieDetailState(
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var videos: Videos? = null
)