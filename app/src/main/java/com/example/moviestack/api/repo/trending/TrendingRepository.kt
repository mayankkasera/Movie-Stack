package com.example.moviestack.api.repo.trending

import android.util.Log
import com.example.moviestack.pojo.MovieList
import com.example.moviestack.pojo.SmallItemList
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingRepository(val trendingRequest: TrendingRequest): TrendingRepositoryI{
    override fun getSmallItemsList(type: SmallItemList.Type): Observable<SmallItemList> {
        return Observable.create<SmallItemList> { emitter ->

            var call : Call<SmallItemList>?

            when(type){
                SmallItemList.Type.TRENDING_MOVIES -> call = trendingRequest?.getTrendingMovies()
                SmallItemList.Type.TRENDING_TV_SHOW -> call = trendingRequest?.getTrendingTvShow()
                else -> call = trendingRequest?.getTrendingMovies()
            }

            call?.enqueue(object : Callback<SmallItemList> {
                override fun onResponse(call: Call<SmallItemList>, response: Response<SmallItemList>) {
                    Log.i("kdsjcn","shdvcjds  : "+response.toString())
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

    override fun getMoviesList(page: String, type: SmallItemList.Type): Observable<MovieList> {
       return Observable.create<MovieList> { emitter ->

                var call : Call<MovieList>?

                when(type){
                    SmallItemList.Type.TRENDING_MOVIES -> call = trendingRequest?.getTrendingMovies(page)
                    SmallItemList.Type.TRENDING_TV_SHOW -> call = trendingRequest?.getTrendingTvShow(page)
                    else -> call = trendingRequest?.getTrendingMovies(page)
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