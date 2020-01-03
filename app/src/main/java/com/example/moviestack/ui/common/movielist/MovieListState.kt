package com.example.moviestack.ui.common.movielist

import com.example.moviestack.ui.moviedetail.review.adapter.ReviewAdapter
import com.example.moviestack.ui.common.movielist.adapter.SimilarAdapter

data class MovieListState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var similarAdapter: SimilarAdapter? = null
)

