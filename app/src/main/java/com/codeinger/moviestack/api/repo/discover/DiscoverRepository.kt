package com.codeinger.moviestack.api.repo.discover

import android.util.Log
import com.codeinger.moviestack.pojo.MovieList
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverRepository(private var discoverRequests: DiscoverRequests?) : DiscoverRepositoryI {

    override fun getGenreMovieList(genre: String): Observable<DiscoverResponce> {
        return Observable.create<DiscoverResponce> { emitter ->
            discoverRequests?.getGenreMovieList(genre)
                ?.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        response.body()?.let {
                            val movieResponse = DiscoverResponce()
                            movieResponse.type = DiscoverResponce.Type.GENRE_MOVIES
                            movieResponse.data = it
                            emitter.onNext(movieResponse)
                            emitter.onComplete()
                        } ?: run {
                            emitter.onNext(DiscoverResponce())
                            emitter.onComplete()
                        }
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        emitter.onError(t)
                        Log.i("dshgczvc", "" + t.message)
                    }
                })
        }
    }

    override fun getGenreMovieList(genre: String, page: String): Observable<DiscoverResponce> {
        return Observable.create<DiscoverResponce> { emitter ->
            discoverRequests?.getGenreMovieList(genre,page)
                ?.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        response.body()?.let {
                            val movieResponse = DiscoverResponce()
                            movieResponse.type = DiscoverResponce.Type.GENRE_MOVIES
                            movieResponse.data = it
                            emitter.onNext(movieResponse)
                            emitter.onComplete()
                        } ?: run {
                            emitter.onNext(DiscoverResponce())
                            emitter.onComplete()
                        }
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        emitter.onError(t)
                        Log.i("dshgczvc", "" + t.message)
                    }
                })
        }
    }


}

