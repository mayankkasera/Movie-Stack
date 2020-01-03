package com.example.moviestack.api.repo.discover

import android.util.Log
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.api.repo.movieInfo.MovieRequests
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverRepository(var discoverRequests: DiscoverRequests?,var genre :String ) : DiscoverRepositoryI {

    override fun getGenreMovieList(): Observable<DiscoverResponce> {
       return Observable.create<DiscoverResponce> {emitter ->
           discoverRequests?.getGenreMovieList(genre)
               ?.enqueue(object : Callback<MovieList>{
                   override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                       response.body()?.let {
                           var movieResponce = DiscoverResponce()
                           movieResponce.type = DiscoverResponce.Type.GENRE_MOVIES
                           movieResponce.data = it
                           emitter.onNext(movieResponce)
                           emitter.onComplete()
                       } ?: run {
                           emitter.onNext(DiscoverResponce())
                           emitter.onComplete()
                       }
                   }
                   override fun onFailure(call: Call<MovieList>, t: Throwable) {
                       emitter.onError(t)
                       Log.i("dshgczvc",""+t.message)
                   }
               })
       }
    }



}

