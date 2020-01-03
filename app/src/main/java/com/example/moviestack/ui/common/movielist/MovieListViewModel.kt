package com.example.moviestack.ui.common.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.repo.movieInforepo.MovieRepositoryI
import com.example.moviestack.ui.common.movielist.adapter.SimilarAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(val movieRepositoryI: MovieRepositoryI) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<MovieListState> = MutableLiveData()
    private var state = MovieListState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getSimilar(){
        movieRepositoryI.getSimilars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var movieList : MovieList = it.data as MovieList
                state = state.copy(similarAdapter = SimilarAdapter(
                    movieList.results
                )
                )
            },{
                state = state.copy(
                    loading = false,
                    failure = true,
                    message = it.localizedMessage
                )
            },{
                state = state.copy(
                    loading = false,
                    success = true
                )
            },{

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: MovieListState) {
        mutableLiveData.postValue(state)
    }
}