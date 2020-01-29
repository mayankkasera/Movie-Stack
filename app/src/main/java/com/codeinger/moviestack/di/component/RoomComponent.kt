package com.codeinger.moviestack.di.component

import android.content.Context
import com.codeinger.moviestack.di.modules.RoomModule
import com.codeinger.moviestack.roomdb.RoomDatabaseHelper
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