package com.example.moviestack.roomdb.mylist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviestack.pojo.MyList


@Dao
interface MyListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyList(result: MyList): Long

    @Query("SELECT * FROM MyList")
    fun getAllMyList(): List<MyList>

    @Query("SELECT * FROM MyList WHERE type == :type")
    fun getAllMyListMovie(type : MyList.Type): List<MyList>

    @Query("DELETE FROM MyList WHERE id == :id")
    fun deleteMyList(id: Int): Int

    @Query("SELECT * FROM MyList WHERE id == :id")
    fun hasMyList(id : Int) : List<MyList>
}