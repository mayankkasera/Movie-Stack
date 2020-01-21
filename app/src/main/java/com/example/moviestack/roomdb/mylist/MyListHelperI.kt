package com.example.moviestack.roomdb.mylist

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.MyList

interface MyListHelperI {


    fun insertMyList(result: MyList): Long


    fun getAllMyList(): List<MyList>

    fun getAllMyListMovie(type : MyList.Type): List<MyList>

    fun deleteMyList(id: Int): Int


    fun hasMyList(id : Int) : Boolean

}