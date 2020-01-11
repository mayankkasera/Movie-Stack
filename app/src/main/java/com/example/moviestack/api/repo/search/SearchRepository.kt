package com.example.moviestack.api.repo.search

import android.util.Log
import com.example.moviestack.api.pojo.Search
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.person.PersonResponce
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository(var searchRequests :SearchRequests ) : SearchRepositoryI {
    override fun getMovie(page: String, query: String,type : SmallItemList.Type): Observable<Search>{
        return Observable.create<Search> { emitter ->

            var call : Call<Search>

            when(type){
                SmallItemList.Type.SEARCH_MOVIES -> call = searchRequests?.getMovie(page,query)
                SmallItemList.Type.SEARCH_TV -> call = searchRequests?.getTv(page,query)
                else -> call = searchRequests?.getMovie(page,query)
            }


            call?.enqueue(object : Callback<Search>{

                override fun onResponse(call: Call<Search>, response: Response<Search>) {

                    Log.i("sdfvghs ","a : ${response.body()}")
                    Log.i("sdfvghs ","a : ${response.toString()}")

                    response.body()?.let {
                        emitter.onNext(it)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(Search())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Search>, t: Throwable) {
                    emitter.onError(t)
                }

            })
        }
    }



}