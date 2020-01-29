package com.codeinger.moviestack.roomdb.movieInfo

import com.codeinger.moviestack.pojo.MovieInfo
import com.codeinger.moviestack.pojo.Result


interface MovieInfoHelperI {

    fun insertResult(result: MovieInfo): Int

    fun getAllResults(): List<Result>

    fun hasMovie(id : String) : Boolean

    fun deleteQrResults(id: Long): Int

}