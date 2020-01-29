package com.codeinger.moviestack.roomdb.mylistdetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codeinger.moviestack.pojo.MovieInfo
import com.codeinger.moviestack.pojo.MyListDetail

@Dao
interface MyListDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyListDetail(result: MyListDetail): Long

    @Query("SELECT * FROM MovieInfo INNER JOIN MyListDetail ON MyListDetail.movieInfoId == MovieInfo.movie_id where MyListDetail.type ==  :type AND myListId == :myListId")
    fun getAllMyListDetail(myListId : Int,type : MyListDetail.Type): List<MovieInfo>

    @Query("DELETE FROM MyListDetail WHERE myListId == :myListId AND movieInfoId = :movieInfoId AND type = :type")
    fun deleteMyListDetail(myListId : Int,movieInfoId : Long,type : MyListDetail.Type): Int

    @Query("SELECT * FROM MyListDetail WHERE myListId == :myListId AND movieInfoId = :movieInfoId AND type = :type"  )
    fun hasMyListDetail(myListId : Int,movieInfoId : Long,type : MyListDetail.Type) : List<MyListDetail>

}