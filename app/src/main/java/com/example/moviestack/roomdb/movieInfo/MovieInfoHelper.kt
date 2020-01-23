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
            Log.i("sdkcns","resultList : ${movieInfo.firstAirDate}")
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
