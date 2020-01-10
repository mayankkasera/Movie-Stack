package com.example.moviestack.api.repo.smallitemlist

import android.util.Log
import com.example.moviestack.api.pojo.MovieList
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
                else -> call = smallItemRequests?.getTopRated()
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
                SmallItemList.Type.TRENDING_MOVIES -> call = smallItemRequests?.getTrendingMovies(page)
                SmallItemList.Type.TRENDING_TV_SHOW -> call = smallItemRequests?.getTrendingTvShow(page)
                SmallItemList.Type.NOW_PLAYING -> call = smallItemRequests?.getNowPlaying(page)
                SmallItemList.Type.UPCOMING -> call = smallItemRequests?.getUpcoming(page)
                SmallItemList.Type.POPULAR -> call = smallItemRequests?.getPopular(page)
                SmallItemList.Type.TOP_RATED -> call = smallItemRequests?.getTopRated(page)
                 else -> call = smallItemRequests?.getTrendingMovies(page)
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