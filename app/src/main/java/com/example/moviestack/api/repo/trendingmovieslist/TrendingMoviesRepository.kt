package com.example.moviestack.api.repo.trendingmovieslist

import android.util.Log
import com.example.moviestack.api.pojo.TrendingMovies
import com.example.moviestack.api.GetRequests
import com.example.moviestack.api.pojo.Trending
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingMoviesRepository(private val getRequests : GetRequests?) :TrendingMoviesRepositoryI{


     override fun getTrendingMovies(): Single<Trending> {
        return Single.create<Trending> { emitter ->


            getRequests?.getTrendingMoviess()?.enqueue(object : Callback<Trending> {
                override fun onResponse(call: Call<Trending>, response: Response<Trending>) {

                    Log.i("xhcbvjd",""+response.body().toString())
                    Log.i("xhcbvjd",""+response.toString())

                    response.body()?.let {
                        emitter.onSuccess(it)
                    } ?: run {
                        emitter.onSuccess(Trending())
                    }

                }

                override fun onFailure(call: Call<Trending>, t: Throwable) {
                    Log.i("xhcbvjd",""+t.toString())
                    emitter.onError(t)
                }
            })
        }
     }

}