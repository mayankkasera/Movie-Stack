package com.example.moviestack.ui.persondetail.info

import com.example.moviestack.pojo.ExternalIds
import com.example.moviestack.pojo.PersonImages
import com.example.moviestack.pojo.PersonInfo
import com.example.moviestack.pojo.TaggedImages
import com.example.moviestack.ui.persondetail.info.adapter.PersonImageAdapter

data class PersonInfoState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var personInfo: PersonInfo? = null,
    var personImageAdapter : PersonImageAdapter? = null
)