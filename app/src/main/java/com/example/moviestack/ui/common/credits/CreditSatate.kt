package com.example.moviestack.ui.common.credits

import com.example.moviestack.ui.common.credits.adapter.CastAdapter

data class CreditSatate (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var castAdapter : CastAdapter? = null
)