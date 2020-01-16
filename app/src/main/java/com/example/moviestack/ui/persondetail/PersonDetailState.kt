package com.example.moviestack.ui.persondetail

import com.example.moviestack.pojo.ExternalIds
import com.example.moviestack.pojo.MovieList
import com.example.moviestack.pojo.TaggedImages
import com.example.moviestack.pojo.Videos

data class PersonDetailState (
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var taggedImages: TaggedImages? = null,
    var externalIds: ExternalIds? = null
)

