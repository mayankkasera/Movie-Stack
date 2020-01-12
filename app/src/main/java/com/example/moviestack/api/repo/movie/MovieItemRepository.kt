package com.example.moviestack.api.repo.movie

import android.util.Log
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.pojo.SmallItemList
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieItemRepository(private val movieItemRequests : MovieItemRequests?) :MovieItemRepositoryI{


     override fun getSmallItemsList(type : SmallItemList.Type): Observable<SmallItemList> {
        return Observable.create<SmallItemList> { emitter ->

            var call : Call<SmallItemList>?

            when(type){
                SmallItemList.Type.NOW_PLAYING -> call = movieItemRequests?.getNowPlaying()
                SmallItemList.Type.UPCOMING -> call = movieItemRequests?.getUpcoming()
                SmallItemList.Type.POPULAR -> call = movieItemRequests?.getPopular()
                SmallItemList.Type.TOP_RATED -> call = movieItemRequests?.getTopRated()
                else -> call = movieItemRequests?.getTopRated()
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

    override fun getMoviesList(page: String, type : SmallItemList.Type): Observable<MovieList> {
        return Observable.create<MovieList> { emitter ->

            var call : Call<MovieList>?

            when(type){

                SmallItemList.Type.NOW_PLAYING -> call = movieItemRequests?.getNowPlaying(page)
                SmallItemList.Type.UPCOMING -> call = movieItemRequests?.getUpcoming(page)
                SmallItemList.Type.POPULAR -> call = movieItemRequests?.getPopular(page)
                SmallItemList.Type.TOP_RATED -> call = movieItemRequests?.getTopRated(page)
                 else -> call = movieItemRequests?.getNowPlaying(page)
            }

            call?.enqueue(object : Callback<MovieList>  {

                override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                    response.body()?.let {
                        emitter.onNext(it)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieList())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    Log.i("kdsjcn",""+t.toString())
                    emitter.onError(t)
                }

            })
        }
    }



}