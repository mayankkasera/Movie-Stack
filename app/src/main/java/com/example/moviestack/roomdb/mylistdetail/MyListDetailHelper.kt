package com.example.moviestack.roomdb.mylistdetail

import android.util.Log
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.MyListDetail
import com.example.moviestack.pojo.Result
import com.example.moviestack.roomdb.LocaleDataBase
import com.example.moviestack.ui.moviedetail.DetailData

class MyListDetailHelper(var qrMovieInfoDataBase: LocaleDataBase) : MyListDetailHelperI{
    override fun insertMyListDetail(result: MyListDetail): Long {
         return qrMovieInfoDataBase.getMyListDetailDao().insertMyListDetail(result)
    }

    override fun getAllMyListDetail(myListId : Int,type : MyListDetail.Type): List<Result> {

        var list : List<MovieInfo> = listOf()
        list = qrMovieInfoDataBase.getMyListDetailDao().getAllMyListDetail(myListId,type)
        Log.i("sdkcns","resultList : ${list.toString()}")
        var resultList = ArrayList<Result>()
        for(movieInfo:MovieInfo in list){
            val s = if(movieInfo?.hasName())(movieInfo?.name_)else(if(movieInfo?.hasTitle())movieInfo?.title else (movieInfo?.originalTitle))
            var result : Result = Result(
                first_air_date = movieInfo?.firstAirDate,
                title = s,
                id = movieInfo?.id,
                releaseDate = movieInfo?.releaseDate,
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

    override fun deleteMyListDetail(myListId : Int,movieInfoId : Long,type : MyListDetail.Type): Int {
        return qrMovieInfoDataBase.getMyListDetailDao().deleteMyListDetail(myListId = myListId,movieInfoId = movieInfoId,type = type)
    }

    override fun hasMyListDetail(
        myListId: Int,
        movieInfoId: Long,
        type: MyListDetail.Type
    ): Boolean {
        return if(qrMovieInfoDataBase.getMyListDetailDao().hasMyListDetail(myListId,movieInfoId,type).size>0) true else false
    }

}