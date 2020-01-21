package com.example.moviestack.roomdb.bookmark

import android.util.Log
import com.example.moviestack.pojo.Bookmark
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.Result
import com.example.moviestack.roomdb.LocaleDataBase
import com.example.moviestack.ui.moviedetail.DetailData

class BookmarkHelper(var qrMovieInfoDataBase: LocaleDataBase) : BookmarkHelperI{


    override fun insertBookmark(movieInfo: MovieInfo,type : DetailData.Type): Int {
        qrMovieInfoDataBase.getMovieInfoDao().insertMovieInfo(movieInfo).toInt()
        when(type){
            DetailData.Type.TV_SHOW -> {
                var bookmark = Bookmark(movieInfoId = movieInfo.id!!,type = Bookmark.Type.TV_SHOW)
                return qrMovieInfoDataBase.getBookMarkDao().insertBookmark(bookmark).toInt()
            }
            DetailData.Type.MOVIE -> {
                var bookmark = Bookmark(movieInfoId = movieInfo.id!!,type = Bookmark.Type.MOVIE)
                return qrMovieInfoDataBase.getBookMarkDao().insertBookmark(bookmark).toInt()
            }
            DetailData.Type.PERSON -> {
                var bookmark = Bookmark(movieInfoId = movieInfo.id!!,type = Bookmark.Type.PERSON)
                return qrMovieInfoDataBase.getBookMarkDao().insertBookmark(bookmark).toInt()
            }
        }

    }

    override fun getAllBookmark(): List<Bookmark> {
        return qrMovieInfoDataBase.getBookMarkDao().getAllBookmark()
    }

    override fun getAllBookmarkMovieInfo(type : DetailData.Type): List<Result> {

        var list : List<MovieInfo> = listOf()

        when(type){
            DetailData.Type.MOVIE -> {
                list  = qrMovieInfoDataBase.getBookMarkDao().getAllBookmarkMovieInfo(Bookmark.Type.MOVIE)
                Log.i("sdkcns","resultList : ${list.toString()} 1")
            }
            DetailData.Type.TV_SHOW -> {
                list = qrMovieInfoDataBase.getBookMarkDao().getAllBookmarkMovieInfo(Bookmark.Type.TV_SHOW)
                Log.i("sdkcns","resultList : ${list.toString()} 2")
            }
            DetailData.Type.PERSON -> {
                list = qrMovieInfoDataBase.getBookMarkDao().getAllBookmarkMovieInfo(Bookmark.Type.PERSON)
                Log.i("sdkcns","resultList : ${list.toString()} 3")
            }
        }



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