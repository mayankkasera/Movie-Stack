package com.example.moviestack.api.repo.smallitemlist

import android.util.Log
import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmallItemRepository(private val smallItemRequests : SmallItemRequests?) :SmallItemRepositoryI{


     override fun getSmallItemsList(type : SmallItemList.Type): Observable<SmallItemList> {
        return Observable.create<SmallItemList> { emitter ->

            var call : Call<SmallItemList>?

            when(type){
                SmallItemList.Type.TRENDING_MOVIES -> call = smallItemRequests?.getTrendingMoviess()
                SmallItemList.Type.TRENDING_TV_SHOW -> call = smallItemRequests?.getTrendingTvShow()
                SmallItemList.Type.NOW_PLAYING -> call = smallItemRequests?.getNowPlaying()
                SmallItemList.Type.UPCOMING -> call = smallItemRequests?.getUpcoming()
                SmallItemList.Type.POPULAR -> call = smallItemRequests?.getPopular()
                SmallItemList.Type.TOP_RATED -> call = smallItemRequests?.getTopRated()
            }

            call?.enqueue(object : Callback<SmallItemList> {
                override fun onResponse(call: Call<SmallItemList>, response: Response<SmallItemList>) {
                    Log.i("kdsjcn","shdvcjds  : "+response.body().toString())
                    response.body()?.let {
                        it.type = type
                        emitter.onNext(it)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(SmallItemList())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<SmallItemList>, t: Throwable) {
                    Log.i("kdsjcn",""+t.toString())
                    emitter.onError(t)
                }
            })
        }
     }

}