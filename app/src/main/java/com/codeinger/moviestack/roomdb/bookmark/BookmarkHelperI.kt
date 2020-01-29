package com.codeinger.moviestack.roomdb.bookmark

import com.codeinger.moviestack.pojo.Bookmark
import com.codeinger.moviestack.pojo.MovieInfo
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.ui.moviedetail.DetailData

interface BookmarkHelperI {

    fun insertBookmark(movieInfo: MovieInfo,type : DetailData.Type): Int

    fun getAllBookmark(): List<Bookmark>

    fun getAllBookmarkMovieInfo(type : DetailData.Type): List<Result>

    fun hasBookmark(id : String) : Boolean

    fun deleteBookmark(id: Long): Int

}