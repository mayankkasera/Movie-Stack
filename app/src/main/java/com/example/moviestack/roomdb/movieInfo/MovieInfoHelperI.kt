package com.example.moviestack.roomdb.movieInfo

import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.Result


interface MovieInfoHelperI {

    fun insertResult(result: MovieInfo): Int

    fun getAllResults(): List<Result>

    fun hasMovie(id : String) : Boolean

    fun deleteQrResults(id: Long): Int

}