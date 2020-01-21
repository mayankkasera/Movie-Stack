package com.example.moviestack.ui.moviedetail.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.pojo.Credits
import com.example.moviestack.pojo.Images
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.Videos
import com.example.moviestack.api.repo.movie.MovieResponse
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.info.adapter.CrewAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.GenreAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.VideosAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class InfoViewModel() : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<InfoState> = MutableLiveData()
    private var state = InfoState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getMovie(id: String, tvShowRepositoryI: MovieItemRepositoryI,type: DetailData.Type) {


        state = state.copy(loading = true)

        compositeDisposable.add(
            Observable.merge(
                tvShowRepositoryI.getMovieInfo(id),
                tvShowRepositoryI.getCredits(id),
                tvShowRepositoryI.getVideos(id),
                tvShowRepositoryI.getImages(id)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    when (it.type) {
                        MovieResponse.Type.MOVIE_INFO -> {
                            var movieInfo: MovieInfo = it.data as MovieInfo
                            Log.i("dkjhfckjds", "success : " + movieInfo.toString())
                            state = state.copy(
                                movieInfo = movieInfo,
                                genreAdapter = GenreAdapter(movieInfo?.genres!!,type)
                            )
                        }
                        MovieResponse.Type.CREDIT -> {
                            var credits: Credits = it.data as Credits
                            if(credits.crew.size>0)
                               state = state.copy(crewAdapter = CrewAdapter(credits.crew))
                        }
                        MovieResponse.Type.VIDEOS -> {
                            var videos: Videos = it.data as Videos
                            if(videos.results.size>0)
                               state = state.copy(videosAdapter = VideosAdapter(videos.results))
                        }

                        MovieResponse.Type.IMAGES -> {

                            var images: Images = it.data as Images
                            Log.i("sdiubcs", images.getPoster())
                            state = state.copy(images = images)
                        }
                    }
                }, {
                    Log.i("dkjhfckjds", "error : " + it.toString())
                    state = state.copy(
                        loading = false,
                        failure = true,
                        message = it.localizedMessage
                    )
                }, {
                    Log.i("dkjhfckjds", "complete : ")
                    state = state.copy(
                        loading = false,
                        success = true
                    )
                }, {

                })

        )


    }

    fun getTvShow(id : String,tvShowRepositoryI: TvShowRepositoryI,type: DetailData.Type){
        state = state.copy(loading = true)

        compositeDisposable.add(
            Observable.merge(
                tvShowRepositoryI.getTvShowInfo(id),
                tvShowRepositoryI.getCredits(id),
                tvShowRepositoryI.getVideos(id),
                tvShowRepositoryI.getImages(id)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    when (it.type) {
                        MovieResponse.Type.MOVIE_INFO -> {
                            var movieInfo: MovieInfo = it.data as MovieInfo
                            Log.i("dkjhfckjds", "success : " + movieInfo.toString())
                            state = state.copy(
                                movieInfo = movieInfo,
                                genreAdapter = GenreAdapter(movieInfo?.genres!!,type)
                            )
                        }
                        MovieResponse.Type.CREDIT -> {
                            var credits: Credits = it.data as Credits
                            if(credits.crew.size>0)
                                state = state.copy(crewAdapter = CrewAdapter(credits.crew))
                        }
                        MovieResponse.Type.VIDEOS -> {
                            var videos: Videos = it.data as Videos
                            if(videos.results.size>0)
                                state = state.copy(videosAdapter = VideosAdapter(videos.results))
                        }

                        MovieResponse.Type.IMAGES -> {

                            var images: Images = it.data as Images
                            Log.i("sdiubcs", images.getPoster())
                            state = state.copy(images = images)
                        }
                    }
                }, {
                    Log.i("dkjhfckjds", "error : " + it.toString())
                    state = state.copy(
                        loading = false,
                        failure = true,
                        message = it.localizedMessage
                    )
                }, {
                    Log.i("dkjhfckjds", "complete : ")
                    state = state.copy(
                        loading = false,
                        success = true
                    )
                }, {

                })

        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: InfoState) {
        mutableLiveData.postValue(state)
    }

}