package com.codeinger.moviestack.pojo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(foreignKeys = [
    ForeignKey(entity = MovieInfo::class,
        parentColumns = arrayOf("movie_id"),
        childColumns = arrayOf("movieInfoId"),
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = MyList::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("myListId"),
        onDelete = ForeignKey.CASCADE)
])
data class MyListDetail(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var movieInfoId : Long = 0,
    var myListId : Int = 0,
    var type : Type = Type.MOVIE
){
    enum class Type{
        MOVIE,TV_SHOW
    }

    class MyListTypeConverter{
        @TypeConverter
        fun getMyListType(data: String?): MyListDetail.Type? {
            val gson = Gson()
            if (data == null) {
                return MyListDetail.Type.MOVIE
            }
            val listType: java.lang.reflect.Type = object : TypeToken<MyListDetail.Type?>() {}.type
            return gson.fromJson<MyListDetail.Type>(data, listType)
        }

        @TypeConverter
        fun setMyListType(type: MyListDetail.Type?): String? {
            val gson = Gson()
            return gson.toJson(type)
        }
    }
}