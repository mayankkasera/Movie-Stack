package com.example.moviestack.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviestack.pojo.*
import com.example.moviestack.roomdb.bookmark.BookmarkDao
import com.example.moviestack.roomdb.movieInfo.MovieInfoDao

@TypeConverters(Bookmark.BookmarkTypeConverter::class)
@Database(entities = [MovieInfo::class,Bookmark::class], version = 1,exportSchema = false)
abstract class LocaleDataBase : RoomDatabase()  {
    abstract fun getMovieInfoDao(): MovieInfoDao

    abstract fun getBookMarkDao(): BookmarkDao
}