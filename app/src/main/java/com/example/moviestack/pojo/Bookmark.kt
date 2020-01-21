package com.example.moviestack.pojo

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = MovieInfo::class,
    parentColumns = arrayOf("movie_id"),
    childColumns = arrayOf("movieInfoId"),
    onDelete = ForeignKey.CASCADE)
))
data class Bookmark (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val movieInfoId: Long,
    val type : Bookmark.Type? = null

){
     enum class Type{
        MOVIE,TV_SHOW,PERSON
    }


    class BookmarkTypeConverter{
        @TypeConverter
        fun getBookmarkType(data: String?): Type? {
            val gson = Gson()
            if (data == null) {
                return Type.MOVIE
            }
            val listType: java.lang.reflect.Type = object : TypeToken<Type?>() {}.type
            return gson.fromJson<Type>(data, listType)
        }

        @TypeConverter
        fun setBookmarkType(type: Type?): String? {
            val gson = Gson()
            return gson.toJson(type)
        }
    }

}


