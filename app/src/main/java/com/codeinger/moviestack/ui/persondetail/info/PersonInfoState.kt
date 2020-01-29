package com.codeinger.moviestack.ui.persondetail.info

import com.codeinger.moviestack.pojo.PersonInfo
import com.codeinger.moviestack.ui.persondetail.info.adapter.PersonImageAdapter

data class PersonInfoState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var personInfo: PersonInfo? = null,
    var personImageAdapter : PersonImageAdapter? = null
)