package com.codeinger.moviestack.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codeinger.moviestack.pojo.*
import com.codeinger.moviestack.roomdb.bookmark.BookmarkDao
import com.codeinger.moviestack.roomdb.movieInfo.MovieInfoDao
import com.codeinger.moviestack.roomdb.mylist.MyListDao
import com.codeinger.moviestack.roomdb.mylistdetail.MyListDetailDao

@TypeConverters(Bookmark.BookmarkTypeConverter::class,MyList.MyListTypeConverter::class,MyListDetail.MyListTypeConverter::class)
@Database(entities = [MovieInfo::class,MyList::class,Bookmark::class,MyListDetail::class], version = 1,exportSchema = false)
abstract class LocaleDataBase : RoomDatabase()  {
    abstract fun getMovieInfoDao(): MovieInfoDao

    abstract fun getBookMarkDao(): BookmarkDao

    abstract fun getMyListDao(): MyListDao

    abstract fun getMyListDetailDao() : MyListDetailDao
}