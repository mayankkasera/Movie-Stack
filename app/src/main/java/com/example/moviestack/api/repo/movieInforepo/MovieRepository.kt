package com.example.moviestack.api.repo.movieInforepo

import android.util.Log
import com.example.moviestack.api.MovieRequests
import com.example.moviestack.api.pojo.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(var movieRequests: MovieRequests?,var id : String) : MovieRepositoryI{

    override fun getMovieInfo(): Observable<MovieResponce> {
        return Observable.create<MovieResponce> { emitter ->


            movieRequests?.getMovieInfo(id)?.enqueue(object : Callback<MovieInfo> {

                override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                    response.body()?.let {
                        var movieResponce : MovieResponce = MovieResponce()
                        movieResponce.type = MovieResponce.Type.MOVIE_INFO
                        movieResponce.data = it
                        emitter.onNext(movieResponce)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponce())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc",""+t.message)
                }

            })
        }
    }



  override  fun getCredits(): Observable<MovieResponce> {
        return Observable.create<MovieResponce>{emitter ->

            movieRequests?.getCredits(id)?.enqueue(object : Callback<Credits>{

                override fun onResponse(call: Call<Credits>, response: Response<Credits>) {
                    response.body()?.let {
                        var movieResponce : MovieResponce = MovieResponce()
                        movieResponce.type = MovieResponce.Type.CREDIT
                        movieResponce.data = it
                        emitter.onNext(movieResponce)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponce())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Credits>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc",""+t.message)
                }



            })

        }
    }

    override fun getImages(): Observable<MovieResponce> {
        return Observable.create<MovieResponce>{emitter ->

            movieRequests?.getImages(id)?.enqueue(object : Callback<Images>{

                override fun onResponse(call: Call<Images>, response: Response<Images>) {
                    response.body()?.let {
                        var movieResponce : MovieResponce = MovieResponce()
                        movieResponce.type = MovieResponce.Type.IMAGES
                        movieResponce.data = it
                        emitter.onNext(movieResponce)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponce())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Images>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc",""+t.message)
                }
            })

        }
    }


    override fun getVideos(): Observable<MovieResponce> {
        return Observable.create<MovieResponce>{emitter ->

            movieRequests?.getVideos(id)?.enqueue(object : Callback<Videos>{

                override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                    response.body()?.let {
                        var movieResponce : MovieResponce = MovieResponce()
                        movieResponce.type = MovieResponce.Type.VIDEOS
                        movieResponce.data = it
                        emitter.onNext(movieResponce)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponce())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Videos>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc",""+t.message)
                }
            })

        }
    }


    override fun getReviews(): Observable<MovieResponce> {
        return Observable.create<MovieResponce>{emitter ->
            movieRequests?.getReviews(id)?.enqueue(object : Callback<Review>{

                override fun onResponse(call: Call<Review>, response: Response<Review>) {
                    Log.i("dshgczvc",""+response.toString())
                    response.body()?.let {
                        Log.i("dshgczvc",""+it.toString())
                        var movieResponce : MovieResponce = MovieResponce()
                        movieResponce.type = MovieResponce.Type.REVIEW
                        movieResponce.data = it
                        emitter.onNext(movieResponce)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponce())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Review>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc",""+t.message)
                }
            })

        }
    }

    override fun getSimilars(): Observable<MovieResponce> {
        return Observable.create<MovieResponce>{emitter ->
            movieRequests?.getSimilar(id)?.enqueue(object : Callback<MovieList>{
                override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                    response.body()?.let {
                        var movieResponce : MovieResponce = MovieResponce()
                        movieResponce.type = MovieResponce.Type.SIMILAR
                        movieResponce.data = it
                        emitter.onNext(movieResponce)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponce())
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