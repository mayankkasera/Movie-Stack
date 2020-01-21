package com.example.moviestack.roomdb.mylistdetail

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.MyListDetail
import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.moviedetail.DetailData

interface MyListDetailHelperI {


    fun insertMyListDetail(result: MyListDetail): Long

    fun getAllMyListDetail(myListId : Int,type : MyListDetail.Type): List<Result>

    fun deleteMyListDetail(myListId : Int,movieInfoId : Long,type : MyListDetail.Type): Int

    fun hasMyListDetail(myListId : Int,movieInfoId : Long,type : MyListDetail.Type) : Boolean


}