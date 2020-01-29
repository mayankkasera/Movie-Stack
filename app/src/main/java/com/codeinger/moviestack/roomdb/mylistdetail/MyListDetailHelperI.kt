package com.codeinger.moviestack.roomdb.mylistdetail

import com.codeinger.moviestack.pojo.MyListDetail
import com.codeinger.moviestack.pojo.Result

interface MyListDetailHelperI {


    fun insertMyListDetail(result: MyListDetail): Long

    fun getAllMyListDetail(myListId : Int,type : MyListDetail.Type): List<Result>

    fun deleteMyListDetail(myListId : Int,movieInfoId : Long,type : MyListDetail.Type): Int

    fun hasMyListDetail(myListId : Int,movieInfoId : Long,type : MyListDetail.Type) : Boolean


}