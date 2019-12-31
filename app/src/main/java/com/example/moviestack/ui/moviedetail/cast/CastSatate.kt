package com.example.moviestack.ui.moviedetail.cast

import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter
import com.example.moviestack.ui.moviedetail.cast.adapter.CastAdapter

data class CastSatate (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var castAdapter : CastAdapter? = null
)