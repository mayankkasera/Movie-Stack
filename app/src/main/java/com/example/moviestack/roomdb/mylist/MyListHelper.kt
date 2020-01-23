package com.example.moviestack.roomdb.mylist

import com.example.moviestack.pojo.MyList
import com.example.moviestack.roomdb.LocaleDataBase

class MyListHelper(var qrMovieInfoDataBase: LocaleDataBase) :MyListHelperI {
    override fun insertMyList(result: MyList): Long {
      return  qrMovieInfoDataBase.getMyListDao().insertMyList(result)
    }

    override fun getAllMyList(): List<MyList> {
        return  qrMovieInfoDataBase.getMyListDao().getAllMyList()
    }

    override fun getMyList(id: Int): MyList {
        return  qrMovieInfoDataBase.getMyListDao().getMyList(id)
    }

    override fun getAllMyListMovie(type: MyList.Type): List<MyList> {
        return  qrMovieInfoDataBase.getMyListDao().getAllMyListMovie(type)
    }

    override fun deleteMyList(id: Int): Int {
        return  qrMovieInfoDataBase.getMyListDao().deleteMyList(id)

    }

    override fun updateMyList(list: MyList): Int {
        return  qrMovieInfoDataBase.getMyListDao().updateMyList(list)
    }

    override fun hasMyList(id: Int): Boolean {
        return if(qrMovieInfoDataBase.getMyListDao().hasMyList(id.toInt()).size>0)
            true
        else
            false
    }
}