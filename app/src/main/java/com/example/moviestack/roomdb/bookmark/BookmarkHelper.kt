package com.example.moviestack.roomdb.bookmark

import android.util.Log
import com.example.moviestack.pojo.Bookmark
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.Result
import com.example.moviestack.roomdb.LocaleDataBase

class BookmarkHelper(var qrMovieInfoDataBase: LocaleDataBase) : BookmarkHelperI{


    override fun insertBookmark(movieInfo: MovieInfo): Int {
        qrMovieInfoDataBase.getMovieInfoDao().insertMovieInfo(movieInfo).toInt()
        var bookmark = Bookmark(movieInfoId = movieInfo.id!!,type = Bookmark.Type.MOVIE)
        return qrMovieInfoDataBase.getBookMarkDao().insertBookmark(bookmark).toInt()
    }

    override fun getAllBookmark(): List<Bookmark> {
        return qrMovieInfoDataBase.getBookMarkDao().getAllBookmark()
    }

    override fun getAllBookmarkMovieInfo(): List<Result> {
        var list : List<MovieInfo> = qrMovieInfoDataBase.getBookMarkDao().getAllBookmarkMovieInfo(Bookmark.Type.MOVIE)
        var resultList = ArrayList<Result>()
        for(movieInfo:MovieInfo in list){
            var result : Result = Result(
                title = movieInfo?.title,
                id = movieInfo?.id,
                originalTitle =  movieInfo?.originalTitle,
                backdropPath = movieInfo?.backdropPath,
                voteAverage = movieInfo?.voteAverage,
                posterPath = movieInfo?.posterPath
            )
            Log.i("sdkcns","resultList : ${result.toString()}")
            resultList.add(result)
        }

        return resultList
    }

    override fun hasBookmark(id: String): Boolean {
        return if(qrMovieInfoDataBase.getBookMarkDao().hasBookmark(id.toInt()).size>0)
            true
        else
            false
    }

    override fun deleteBookmark(id: Long): Int {
        return qrMovieInfoDataBase.getBookMarkDao().deleteBookmark(id.toInt())
    }

}