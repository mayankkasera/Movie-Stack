package com.example.moviestack.pojo


import android.util.Log
import com.google.gson.annotations.SerializedName

data class ExternalIds(
    @SerializedName("facebook_id")
    var facebookId: String = "",
    @SerializedName("freebase_id")
    var freebaseId: String = "",
    @SerializedName("freebase_mid")
    var freebaseMid: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("imdb_id")
    var imdbId: String = "",
    @SerializedName("instagram_id")
    var instagramId: String = "",
    @SerializedName("tvrage_id")
    var tvrageId: Int = 0,
    @SerializedName("twitter_id")
    var twitterId: String = ""
){
   fun getFbState() : Boolean{
       Log.i("sdjcbhjs","$facebookId")
       if(facebookId==null)
           return false
       else
           return true
    }

    fun getInstaState() : Boolean{
        Log.i("sdjcbhjs","$instagramId")
        if(instagramId==null)
            return false
        else
            return true
    }

    fun getTwitterState() : Boolean{
        Log.i("sdjcbhjs","$twitterId")
        if(twitterId==null)
            return false
        else
            return true
    }

    fun getImdbIdState() : Boolean{
        Log.i("sdjcbhjs","$imdbId")
        if(imdbId==null)
            return false
        else
            return true
    }
}