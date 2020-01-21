package com.example.moviestack.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviestack.pojo.*
import com.example.moviestack.roomdb.bookmark.BookmarkDao
import com.example.moviestack.roomdb.movieInfo.MovieInfoDao
import com.example.moviestack.roomdb.mylist.MyListDao
import com.example.moviestack.roomdb.mylistdetail.MyListDetailDao

@TypeConverters(Bookmark.BookmarkTypeConverter::class,MyList.MyListTypeConverter::class,MyListDetail.MyListTypeConverter::class)
@Database(entities = [MovieInfo::class,MyList::class,Bookmark::class,MyListDetail::class], version = 1,exportSchema = false)
abstract class LocaleDataBase : RoomDatabase()  {
    abstract fun getMovieInfoDao(): MovieInfoDao

    abstract fun getBookMarkDao(): BookmarkDao

    abstract fun getMyListDao(): MyListDao

    abstract fun getMyListDetailDao() : MyListDetailDao
}