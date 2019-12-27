package com.example.moviestack.api.repo.trendingmovieslist

import android.util.Log
import com.example.moviestack.api.GetRequests
import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmallItemRepository(private val getRequests : GetRequests?,private val type : SmallItemList.Type) :SmallItemRepositoryI{


     override fun getTrendingMovies(): Observable<SmallItemList> {
        return Observable.create<SmallItemList> { emitter ->


            var call : Call<SmallItemList>?

            when(type){
                SmallItemList.Type.TRENDING_MOVIES -> call = getRequests?.getTrendingMoviess()
                SmallItemList.Type.TRENDING_TV_SHOW -> call = getRequests?.getTrendingTvShow()
                SmallItemList.Type.NOW_PLAYING -> call = getRequests?.getNowPlaying()
                SmallItemList.Type.UPCOMING -> call = getRequests?.getUpcoming()
            }



            call?.enqueue(object : Callback<SmallItemList> {
                override fun onResponse(call: Call<SmallItemList>, response: Response<SmallItemList>) {

                    response.body()?.let {
                        when(type){
                            SmallItemList.Type.TRENDING_MOVIES ->  it.type = SmallItemList.Type.TRENDING_MOVIES
                            SmallItemList.Type.TRENDING_TV_SHOW ->  it.type = SmallItemList.Type.TRENDING_TV_SHOW
                            SmallItemList.Type.NOW_PLAYING ->  it.type = SmallItemList.Type.NOW_PLAYING
                            SmallItemList.Type.UPCOMING ->  it.type = SmallItemList.Type.UPCOMING
                        }
                        emitter.onNext(it)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(SmallItemList())
                        emitter.onComplete()
                    }

                }

                override fun onFailure(call: Call<SmallItemList>, t: Throwable) {
                    Log.i("xhcbvjd",""+t.toString())
                    emitter.onError(t)
                }
            })
        }
     }

}