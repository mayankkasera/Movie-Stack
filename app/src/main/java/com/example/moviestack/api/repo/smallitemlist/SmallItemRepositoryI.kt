package com.example.moviestack.api.repo.smallitemlist

import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable

interface SmallItemRepositoryI {
    fun getSmallItemsList(type : SmallItemList.Type) : Observable<SmallItemList>
}