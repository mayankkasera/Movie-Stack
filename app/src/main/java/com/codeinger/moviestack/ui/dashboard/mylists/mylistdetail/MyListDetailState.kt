package com.codeinger.moviestack.ui.dashboard.mylists.mylistdetail

import com.codeinger.moviestack.ui.dashboard.mylists.mylistdetail.adapter.MyListDetailAdapter

data class MyListDetailState(var loading: Boolean = false,
                        var success: Boolean = false,
                        var failure: Boolean = false,
                        var message: String? = null,
                        var myListDetailAdapter : MyListDetailAdapter? = null)