package com.example.moviestack.ui.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.pojo.Videos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel() : ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<MovieDetailState> = MutableLiveData()
    private var state = MovieDetailState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getMovieMedia(id : String,movieItemRepositoryI: MovieItemRepositoryI){

        state = state.copy(loading = true)

        compositeDisposable.add(
            movieItemRepositoryI.getVideos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val videos : Videos = it.data as Videos
                    state = state.copy(videos = videos)

                },{
                    state = state.copy(
                        loading = false,
                        failure = true,
                        message = it.localizedMessage
                    )
                },{
                    Log.i("sddhvgch","complete : ")
                    state = state.copy(
                        loading = false,
                        success = true
                    )
                },{

                })

        )
    }

    fun getTvShowMedia(id :String,tvShowRepositoryI: TvShowRepositoryI){
        state = state.copy(loading = true)

        compositeDisposable.add(
            tvShowRepositoryI.getVideos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val videos : Videos = it.data as Videos
                    state = state.copy(videos = videos)

                },{
                    state = state.copy(
                        loading = false,
                        failure = true,
                        message = it.localizedMessage
                    )
                },{
                    Log.i("sddhvgch","complete : ")
                    state = state.copy(
                        loading = false,
                        success = true
                    )
                },{

                })

        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: MovieDetailState) {
        mutableLiveData.postValue(state)
    }
}