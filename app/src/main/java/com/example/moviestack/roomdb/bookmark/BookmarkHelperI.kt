package com.example.moviestack.roomdb.bookmark

import com.example.moviestack.pojo.Bookmark
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.Result

interface BookmarkHelperI {

    fun insertBookmark(movieInfo: MovieInfo): Int

    fun getAllBookmark(): List<Bookmark>

    fun getAllBookmarkMovieInfo(): List<Result>

    fun hasBookmark(id : String) : Boolean

    fun deleteBookmark(id: Long): Int

}