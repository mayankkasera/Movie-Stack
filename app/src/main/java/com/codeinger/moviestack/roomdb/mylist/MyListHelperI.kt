package com.codeinger.moviestack.roomdb.mylist

import com.codeinger.moviestack.pojo.MyList

interface MyListHelperI {


    fun insertMyList(result: MyList): Long


    fun getAllMyList(): List<MyList>

    fun getAllMyListMovie(type : MyList.Type): List<MyList>

    fun deleteMyList(id: Int): Int

    fun updateMyList(list : MyList): Int

    fun getMyList(id: Int): MyList

    fun hasMyList(id : Int) : Boolean

}