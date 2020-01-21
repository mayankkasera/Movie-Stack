package com.example.moviestack.roomdb.bookmark

import com.example.moviestack.pojo.Bookmark
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.moviedetail.DetailData

interface BookmarkHelperI {

    fun insertBookmark(movieInfo: MovieInfo,type : DetailData.Type): Int

    fun getAllBookmark(): List<Bookmark>

    fun getAllBookmarkMovieInfo(type : DetailData.Type): List<Result>

    fun hasBookmark(id : String) : Boolean

    fun deleteBookmark(id: Long): Int

}