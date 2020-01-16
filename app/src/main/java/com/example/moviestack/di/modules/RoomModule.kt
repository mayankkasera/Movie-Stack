package com.example.moviestack.di.modules

import android.content.Context
import androidx.room.Room
import com.example.moviestack.roomdb.LocaleDataBase
import com.example.moviestack.roomdb.RoomConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RoomModule{

    @Provides
    @Singleton
    fun getLocaleDataBase(context: Context) : LocaleDataBase {
        return Room.databaseBuilder(context.applicationContext,
             LocaleDataBase::class.java, RoomConstants.DB_NAME)
            .allowMainThreadQueries()
            .build()
    }

}