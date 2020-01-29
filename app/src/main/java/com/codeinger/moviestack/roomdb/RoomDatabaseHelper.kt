package com.codeinger.moviestack.roomdb

import com.codeinger.moviestack.utils.App
import javax.inject.Inject

class RoomDatabaseHelper {

    init {
        App.roomComponent()?.inject(this)
    }

    @Inject
    lateinit var localeDataBase: LocaleDataBase

}