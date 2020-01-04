package com.example.moviestack.ui.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.Videos
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(val movieRepositoryI: MovieRepositoryI) : ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<MovieDetailState> = MutableLiveData()
    private var state = MovieDetailState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getMedia(id : String){
        compositeDisposable.add(
            movieRepositoryI.getVideos(id)
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