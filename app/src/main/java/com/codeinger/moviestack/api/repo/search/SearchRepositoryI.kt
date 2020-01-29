package com.codeinger.moviestack.api.repo.search

import com.codeinger.moviestack.pojo.Search
import com.codeinger.moviestack.pojo.SmallItemList
import io.reactivex.Observable

interface SearchRepositoryI {
    fun getMovie(page : String,query : String,type : SmallItemList.Type) : Observable<Search>
}