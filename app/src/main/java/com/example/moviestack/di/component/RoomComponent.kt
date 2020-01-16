package com.example.moviestack.di.component

import android.content.Context
import com.example.moviestack.di.modules.DataModule
import com.example.moviestack.di.modules.RoomModule
import com.example.moviestack.roomdb.RoomDatabaseHelper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface RoomComponent {

    fun inject(roomDatabaseHelper: RoomDatabaseHelper)


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): RoomComponent
    }
}