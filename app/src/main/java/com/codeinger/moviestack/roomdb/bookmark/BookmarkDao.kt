package com.codeinger.moviestack.roomdb.bookmark

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codeinger.moviestack.pojo.Bookmark
import com.codeinger.moviestack.pojo.MovieInfo

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(result: Bookmark): Long

    @Query("SELECT * FROM Bookmark")
    fun getAllBookmark(): List<Bookmark>

    @Query("DELETE FROM Bookmark WHERE movieInfoId = :id")
    fun deleteBookmark(id: Int): Int

    @Query("SELECT * FROM Bookmark WHERE  movieInfoId == (:id)")
    fun hasBookmark(id : Int) : List<Bookmark>

    @Query("SELECT * FROM MovieInfo INNER JOIN Bookmark ON MovieInfo.movie_id == Bookmark.movieInfoId WHERE Bookmark.type == (:type)"  )
    fun getAllBookmarkMovieInfo(type : Bookmark.Type) : List<MovieInfo>

}