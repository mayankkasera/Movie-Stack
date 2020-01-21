package com.example.moviestack.ui.dashboard.mylists.mylistdetail

import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter
import com.example.moviestack.ui.dashboard.mylists.mylistdetail.adapter.MyListDetailAdapter
import com.example.moviestack.ui.moviedetail.info.dialog.adapter.MyListAdapter

data class MyListDetailState(var loading: Boolean = false,
                        var success: Boolean = false,
                        var failure: Boolean = false,
                        var message: String? = null,
                        var myListDetailAdapter : MyListDetailAdapter? = null)