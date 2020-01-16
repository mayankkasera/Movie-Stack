package com.example.moviestack.roomdb.movieInfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviestack.pojo.*

@Dao
interface MovieInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieInfo(result: MovieInfo): Long

    @Query("SELECT * FROM MovieInfo")
    fun getAllMovieInfo(): List<MovieInfo>

    @Query("DELETE FROM MovieInfo WHERE id = :id")
    fun deleteQrResults(id: Long): Int

    @Query("SELECT * FROM MovieInfo WHERE  id == (:id)")
    fun hasMovie(id : Int) : List<MovieInfo>

}