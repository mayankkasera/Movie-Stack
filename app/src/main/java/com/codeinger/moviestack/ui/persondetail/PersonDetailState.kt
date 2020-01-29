package com.codeinger.moviestack.ui.persondetail

import com.codeinger.moviestack.pojo.ExternalIds
import com.codeinger.moviestack.pojo.TaggedImages

data class PersonDetailState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var taggedImages: TaggedImages? = null,
    var externalIds: ExternalIds? = null
)

