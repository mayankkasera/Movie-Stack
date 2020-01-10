package com.example.moviestack.ui.moviedetail.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.api.pojo.Images
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.api.pojo.Videos
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.api.repo.movieInfo.MovieResponse
import com.example.moviestack.ui.moviedetail.info.adapter.CrewAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.GenreAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.VideosAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class InfoViewModel(val movieRepositoryI: MovieRepositoryI) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<InfoState> = MutableLiveData()
    private var state = InfoState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getMovieInfo(id: String) {


        state = state.copy(loading = true)

        compositeDisposable.add(
            Observable.merge(
                movieRepositoryI.getMovieInfo(id),
                movieRepositoryI.getCredits(id),
                movieRepositoryI.getVideos(id),
                movieRepositoryI.getImages(id)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("dkjhfckjds", "success : " + it.toString())
                    when (it.type) {
                        MovieResponse.Type.MOVIE_INFO -> {
                            var movieInfo: MovieInfo = it.data as MovieInfo
                            state = state.copy(
                                movieInfo = movieInfo,
                                genreAdapter = GenreAdapter(movieInfo.genres)
                            )
                        }
                        MovieResponse.Type.CREDIT -> {
                            var credits: Credits = it.data as Credits
                            state = state.copy(crewAdapter = CrewAdapter(credits.crew))
                        }
                        MovieResponse.Type.VIDEOS -> {
                            var videos: Videos = it.data as Videos
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