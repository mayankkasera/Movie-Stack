package com.codeinger.moviestack.di.modules

import android.content.Context
import androidx.room.Room
import com.codeinger.moviestack.roomdb.LocaleDataBase
import com.codeinger.moviestack.roomdb.RoomConstants
import dagger.Module
import dagger.Provides
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