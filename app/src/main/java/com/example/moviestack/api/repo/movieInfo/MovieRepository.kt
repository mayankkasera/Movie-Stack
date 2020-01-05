package com.example.moviestack.api.repo.movieInfo

import android.util.Log
import com.example.moviestack.api.pojo.*
import com.example.moviestack.utils.NetworkConstants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(var movieRequests: MovieRequests?) : MovieRepositoryI {

    override fun getMovieInfo(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->
            movieRequests?.getMovieInfo(id)?.enqueue(object : Callback<MovieInfo> {

                override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {

                    response.body()?.let {
                        val movieResponse = MovieResponse()
                        movieResponse.type = MovieResponse.Type.MOVIE_INFO
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponse())
                        emitter.onComplete()
                    }

                }

                override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc", "" + t.message)
                }

            })
        }
    }


    override fun getCredits(id: String): Observable<MovieResponse> {

        return Observable.create<MovieResponse> { emitter ->

            movieRequests?.getCredits(id)?.enqueue(object : Callback<Credits> {

                override fun onResponse(call: Call<Credits>, response: Response<Credits>) {
                    Log.i("dchjdbjsd",""+response.body().toString())
                    response.body()?.let {

                        val movieResponse = MovieResponse()
                        movieResponse.type = MovieResponse.Type.CREDIT
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponse())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Credits>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc", "" + t.message)
                }


            })

        }
    }

    override fun getImages(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->

            movieRequests?.getImages(id)?.enqueue(object : Callback<Images> {

                override fun onResponse(call: Call<Images>, response: Response<Images>) {
                    response.body()?.let {
                        val movieResponse = MovieResponse()
                        movieResponse.type = MovieResponse.Type.IMAGES
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponse())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Images>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc", "" + t.message)
                }
            })

        }
    }


    override fun getVideos(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->

            movieRequests?.getVideos(id)?.enqueue(object : Callback<Videos> {

                override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                    response.body()?.let {
                        val movieResponse = MovieResponse()
                        movieResponse.type = MovieResponse.Type.VIDEOS
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponse())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Videos>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc", "" + t.message)
                }
            })

        }
    }


    override fun getReviews(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->
            movieRequests?.getReviews(id)?.enqueue(object : Callback<Review> {

                override fun onResponse(call: Call<Review>, response: Response<Review>) {
                    Log.i("dshgczvc", "" + response.toString())
                    response.body()?.let {
                        Log.i("dshgczvc", "" + it.toString())
                        val movieResponse = MovieResponse()
                        movieResponse.type = MovieResponse.Type.REVIEW
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponse())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<Review>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc", "" + t.message)
                }
            })

        }
    }

    override fun getSimilars(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->
            movieRequests?.getSimilar(id)?.enqueue(object : Callback<MovieList> {
                override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                    response.body()?.let {
                        val movieResponse = MovieResponse()
                        movieResponse.type = MovieResponse.Type.SIMILAR
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(MovieResponse())
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