package com.example.moviestack.api.repo.tvshow

import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable

interface TvShowRepositoryI {

    fun getTvShow(type : SmallItemList.Type) : Observable<SmallItemList>

}