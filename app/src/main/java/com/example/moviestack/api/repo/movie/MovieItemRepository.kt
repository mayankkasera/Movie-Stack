package com.example.moviestack.api.repo.movie

import android.util.Log
import com.example.moviestack.api.repo.movie.MovieResponse
import com.example.moviestack.pojo.*
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


    override fun getMovieInfo(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->
            movieItemRequests?.getMovieInfo(id)?.enqueue(object : Callback<MovieInfo> {

                override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {

                    Log.i("sdjdsvc","${response.toString()}")
                    Log.i("sdjdsvc","${response.body()}")

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

            movieItemRequests?.getCredits(id)?.enqueue(object : Callback<Credits> {

                override fun onResponse(call: Call<Credits>, response: Response<Credits>) {
                    Log.i("dchjdbjsd",""+response.toString())
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

            movieItemRequests?.getImages(id)?.enqueue(object : Callback<Images> {

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

            movieItemRequests?.getVideos(id)?.enqueue(object : Callback<Videos> {

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
            movieItemRequests?.getReviews(id)?.enqueue(object : Callback<Review> {

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
            movieItemRequests?.getSimilar(id)?.enqueue(object : Callback<MovieList> {
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

    override fun getSimilars(id: String, page: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->
            movieItemRequests?.getSimilar(id,page)?.enqueue(object : Callback<MovieList> {
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