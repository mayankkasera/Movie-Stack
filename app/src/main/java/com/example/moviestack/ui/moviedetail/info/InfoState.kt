package com.example.moviestack.ui.moviedetail.info

import com.example.moviestack.api.pojo.Images
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.ui.moviedetail.info.adapter.CrewAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.GenreAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.VideosAdapter

data class InfoState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var images : Images? = null,
    var movieInfo: MovieInfo? = null,
    var genreAdapter: GenreAdapter? = null,
    var crewAdapter : CrewAdapter? = null,
    var videosAdapter: VideosAdapter? = null

    )