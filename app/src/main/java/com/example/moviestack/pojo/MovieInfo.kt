package com.example.moviestack.pojo


import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


@Entity
@TypeConverters(
    MovieInfo.EpisodeRunTimeConverter::class,
    MovieInfo.GenreConverter::class,
    MovieInfo.ProductionCompaniesConverter::class,
    MovieInfo.ProductionCountryConverter::class,
    MovieInfo.SpokenLanguageConverter::class
)


data class MovieInfo(

    @SerializedName("first_air_date")
    var firstAirDate: String? = "",

    @SerializedName("name")
    var name_: String? = "",

    @SerializedName("episode_run_time")
    var episodeRunTime: List<Int> = listOf(),

    @SerializedName("adult")
    var adult: Boolean? = false,
    @SerializedName("backdrop_path")
    var backdropPath: String? = "",
    @SerializedName("budget")
    var budgetOfMovie: Long? = 0,



    @SerializedName("genres")
    var genres: List<Genre>? = listOf(),


    @SerializedName("homepage")
    var homepage: String? = "",
    @PrimaryKey()
    @SerializedName("id")
    var id: Long? = 0,
    @SerializedName("imdb_id")
    var imdbId: String? = "",
    @SerializedName("original_language")
    var originalLanguage: String? = "",
    @SerializedName("original_title")
    var originalTitle: String? = "",
    @SerializedName("overview")
    var overview: String? = "",
    @SerializedName("popularity")
    var popularity: Double? = 0.0,
    @SerializedName("poster_path")
    var posterPath: String? = "",


    @SerializedName("production_companies")
    var productionCompany: List<ProductionCompany>? = listOf(),


    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountry>? = listOf(),


    @SerializedName("release_date")
    var releaseDate: String? = "",
    @SerializedName("revenue")
    var revenueOfMovie: Long? = 0,
    @SerializedName("runtime")
    var runtimeOfMovie: Int? = 0,

    @TypeConverters(SpokenLanguageConverter::class)
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>? = listOf(),


    @SerializedName("status")
    var status: String? = "",
    @SerializedName("tagline")
    var tagline: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("video")
    var video: Boolean? = false,
    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int? = 0
) {





    data class Genre(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class ProductionCompany(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("logo_path")
        var logoPath: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("origin_country")
        var originCountry: String? = ""
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        var iso31661: String? = "",
        @SerializedName("name")
        var name: String? = ""
    )

    data class SpokenLanguage(
        @SerializedName("iso_639_1")
        var iso6391: String? = "",
        @SerializedName("name")
        var name: String? = ""
    )


    fun getLanguage():String?{
        return if(spokenLanguages?.size!! >0)
            spokenLanguages?.get(0)?.name
        else if(originalLanguage?.length!! >0)
            originalLanguage
        else
            ""
    }

    fun getreleaseDate():String?{
        return if(releaseDate.equals("")) firstAirDate!! else  releaseDate!!
    }

    fun getName():String?{
        return if(title.equals("")) originalTitle!! else  title!!
    }

    fun getYear():String{

        if(releaseDate?.length!!>0){
            val parts = releaseDate?.split("-")
            Log.i("jhcbvxd"," "+parts)
            return parts!![0]
        }
        else{
            val parts = firstAirDate?.split("-")
            Log.i("jhcbvxd"," "+parts)
            return parts!![0]
        }

    }

    fun getProductionCompanies() : String{
        var string : String = ""
        for(productionCompany : ProductionCompany in this.productionCompany!!){
            string += productionCompany.name+", "
        }

        if(string.length>2)
            string = string.substring(0,string.length-2)

        return string
    }

    fun getBudget():String{
        return "$"+ budgetOfMovie
    }

    fun getRevenue():String{
        return "$"+ budgetOfMovie
    }

    fun getRuntime (): String{
        if (runtimeOfMovie != null&& runtimeOfMovie!! > 0) {
            val hours: Int = runtimeOfMovie!! / 60 //since both are ints, you get an int
            val minutes: Int = runtimeOfMovie!! % 60
            return "$hours hour $minutes minutes"
        }
        else if(episodeRunTime.size>0){
            Log.i("sdlcns","sdfsdfc ${episodeRunTime[0]}")
            val hours: Int = episodeRunTime[0]!! / 60 //since both are ints, you get an int
            val minutes: Int = episodeRunTime[0]!! % 60
            return "$hours hour $minutes minutes"
        }

        else
            return ""
    }




    class SpokenLanguageConverter{
        @TypeConverter
        fun getSpokenLanguage(data: String?): List<SpokenLanguage?>? {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<SpokenLanguage?>?>() {}.type
            return gson.fromJson<List<SpokenLanguage?>>(data, listType)
        }

        @TypeConverter
        fun setSpokenLanguage(myObjects: List<SpokenLanguage?>?): String? {
            val gson = Gson()
            return gson.toJson(myObjects)
        }

    }

    class ProductionCountryConverter{
        @TypeConverter
        fun getProductionCountry(data: String?): List<ProductionCountry?>? {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<ProductionCountry?>?>() {}.type
            return gson.fromJson<List<ProductionCountry?>>(data, listType)
        }

        @TypeConverter
        fun setProductionCountry(myObjects: List<ProductionCountry?>?): String? {
            val gson = Gson()
            return gson.toJson(myObjects)
        }

    }

    class ProductionCompaniesConverter{
        @TypeConverter
        fun getProductionCompany(data: String?): List<ProductionCompany?>? {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<ProductionCompany?>?>() {}.type
            return gson.fromJson<List<ProductionCompany?>>(data, listType)
        }

        @TypeConverter
        fun setProductionCompany(myObjects: List<ProductionCompany?>?): String? {
            val gson = Gson()
            return gson.toJson(myObjects)
        }
    }

    class GenreConverter{
        @TypeConverter
        fun getGenre(data: String?): List<MovieInfo.Genre?>? {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<MovieInfo.Genre?>?>() {}.type
            return gson.fromJson<List<MovieInfo.Genre?>>(data, listType)
        }

        @TypeConverter
        fun setGenre(myObjects: List<MovieInfo.Genre?>?): String? {
            val gson = Gson()
            return gson.toJson(myObjects)
        }
    }

    class EpisodeRunTimeConverter{
        @TypeConverter
        fun getEpisodeRunTime(data: String?): List<Int>? {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<Int>?>() {}.type
            return gson.fromJson<List<Int>>(data, listType)
        }

        @TypeConverter
        fun setEpisodeRunTime(myObjects: List<Int>?): String? {
            val gson = Gson()
            return gson.toJson(myObjects)
        }
    }





}