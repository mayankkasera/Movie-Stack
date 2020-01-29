package com.codeinger.moviestack.api.repo.tvshow

import android.util.Log
import com.codeinger.moviestack.api.repo.movie.MovieResponse
import com.codeinger.moviestack.pojo.*
import com.codeinger.tvstack.api.repo.tvshow.TvShowRequests
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepository(val tvShowRequests: TvShowRequests): TvShowRepositoryI{


    override fun getSmallItemsList(type : SmallItemList.Type): Observable<SmallItemList> {
        return Observable.create<SmallItemList> { emitter ->

            var call : Call<SmallItemList>?

            when(type){
                SmallItemList.Type.TOP_RATED_TV_SHOW -> call = tvShowRequests?.getTopRatedTvShow()
                SmallItemList.Type.POPULAR_TV_SHOW -> call = tvShowRequests?.getPopularTvShow()

                else -> call = tvShowRequests?.getPopularTvShow()
            }

            call?.enqueue(object : Callback<SmallItemList> {
                override fun onResponse(call: Call<SmallItemList>, response: Response<SmallItemList>) {
                    Log.i("kdsjcn","shdvcjds  : "+response.body().toString())
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

    override fun getTvShowList(page: String, type : SmallItemList.Type): Observable<MovieList> {
        return Observable.create<MovieList> { emitter ->

            var call : Call<MovieList>?

            when(type){
                SmallItemList.Type.POPULAR_TV_SHOW -> call = tvShowRequests?.getPopular(page)
                SmallItemList.Type.TOP_RATED_TV_SHOW -> call = tvShowRequests?.getTopRated(page)
                else -> call = tvShowRequests?.getPopular(page)
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

    override fun getTvShowInfo(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->
            tvShowRequests?.getTvShowInfo(id)?.enqueue(object : Callback<MovieInfo> {

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

            tvShowRequests?.getCredits(id)?.enqueue(object : Callback<Credits> {

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

    override fun getVideos(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->

            tvShowRequests?.getVideos(id)?.enqueue(object : Callback<Videos> {

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

    override fun getImages(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->

            tvShowRequests?.getImages(id)?.enqueue(object : Callback<Images> {

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

    override fun getReviews(id: String): Observable<MovieResponse> {
        return Observable.create<MovieResponse> { emitter ->
            tvShowRequests?.getReviews(id)?.enqueue(object : Callback<Review> {

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
            tvShowRequests?.getSimilar(id)?.enqueue(object : Callback<MovieList> {
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
            tvShowRequests?.getSimilar(id,page)?.enqueue(object : Callback<MovieList> {
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