package com.example.moviestack.api.repo.search

import com.example.moviestack.api.pojo.Search
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.person.PersonResponce
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Query

interface SearchRepositoryI {
    fun getMovie(page : String,query : String,type : SmallItemList.Type) : Observable<Search>
}