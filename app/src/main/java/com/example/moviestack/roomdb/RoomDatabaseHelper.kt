package com.example.moviestack.roomdb

import com.example.moviestack.utils.App
import javax.inject.Inject

class RoomDatabaseHelper {

    init {
        App.roomComponent()?.inject(this)
    }

    @Inject
    lateinit var localeDataBase: LocaleDataBase

}