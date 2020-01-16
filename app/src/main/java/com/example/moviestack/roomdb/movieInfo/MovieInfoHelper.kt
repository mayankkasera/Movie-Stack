package com.example.qrcode.roomdb.utils

import android.util.Log
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.Result
import com.example.moviestack.roomdb.LocaleDataBase
import com.example.moviestack.roomdb.movieInfo.MovieInfoHelperI
import kotlin.collections.ArrayList

class MovieInfoHelper(var qrMovieInfoDataBase: LocaleDataBase) :
    MovieInfoHelperI {


    override fun insertResult(result: MovieInfo): Int {
        Log.i("jvfbbv","dshjcvdf : ${result.toString()}")
        return qrMovieInfoDataBase.getMovieInfoDao().insertMovieInfo(result).toInt()
    }

    override fun getAllResults(): List<Result> {
        var list : List<MovieInfo> = qrMovieInfoDataBase.getMovieInfoDao().getAllMovieInfo()
        Log.i("sdkcns","list : ${list.toString()}")
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
        Log.i("sdkcns","resultList : ${resultList.toString()}")
        return resultList
    }

    override fun hasMovie(id: String): Boolean {
        return if(qrMovieInfoDataBase.getMovieInfoDao().hasMovie(id.toInt()).size>0)
            true
        else
            false

    }

    override fun deleteQrResults(id: Long): Int {
        return qrMovieInfoDataBase.getMovieInfoDao().deleteQrResults(id)
    }


}
