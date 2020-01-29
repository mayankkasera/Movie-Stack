package com.codeinger.moviestack.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.codeinger.moviestack.api.NetworkConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MyList (
   @PrimaryKey(autoGenerate = true)
   var id : Int = 0,
   var name : String = "",
   var size : Int = 0,
   var type : Type = Type.MOVIE,
   var image : String = ""
) : Parcelable {

   fun getImageUrl() : String{
      return NetworkConstants.baseImageUrl500+image
   }

   @Parcelize
   enum class Type : Parcelable {
       MOVIE,TV_SHOW
   }

   class MyListTypeConverter{
      @TypeConverter
      fun getMyListType(data: String?): MyList.Type? {
         val gson = Gson()
         if (data == null) {
            return MyList.Type.MOVIE
         }
         val listType: java.lang.reflect.Type = object : TypeToken<MyList.Type?>() {}.type
         return gson.fromJson<MyList.Type>(data, listType)
      }

      @TypeConverter
      fun setMyListType(type: MyList.Type?): String? {
         val gson = Gson()
         return gson.toJson(type)
      }
   }

}