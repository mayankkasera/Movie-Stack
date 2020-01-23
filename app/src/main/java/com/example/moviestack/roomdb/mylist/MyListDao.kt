package com.example.moviestack.roomdb.mylist

import androidx.room.*
import com.example.moviestack.pojo.MyList


@Dao
interface MyListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyList(result: MyList): Long

    @Query("SELECT * FROM MyList")
    fun getAllMyList(): List<MyList>

    @Query("SELECT * FROM MyList WHERE id == :id")
    fun getMyList(id: Int): MyList

    @Query("SELECT * FROM MyList WHERE type == :type")
    fun getAllMyListMovie(type : MyList.Type): List<MyList>

    @Query("DELETE FROM MyList WHERE id == :id")
    fun deleteMyList(id: Int): Int

    @Update
    fun updateMyList(list : MyList): Int

    @Query("SELECT * FROM MyList WHERE id == :id")
    fun hasMyList(id : Int) : List<MyList>
}